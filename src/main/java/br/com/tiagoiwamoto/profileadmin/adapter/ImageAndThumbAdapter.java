package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomainWithImage;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ImageAndThumbAdapter {

    private final Double outputQuality = 0.7;
    private final Integer maxHeight = 200;

    public ImageDto storeImage(MultipartFile multipartFile, Path path){
        log.info("iniciando gravação da imagem ao path");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String originalFileName = fileName.concat(".").concat(fileExtension);
        String thumbnail = fileName.concat("_th").concat(".").concat(fileExtension);
        try{
            if(!path.toFile().exists()){
                log.info("diretório não existe e será criado");
                path.toFile().mkdirs();
            }
            Files.copy(multipartFile.getInputStream(), path.resolve(originalFileName));
            log.info("imagem foi transferida com sucesso");
            Thumbnails.of(new File(path.toString().concat(File.separator).concat(originalFileName)))
                    .height(maxHeight)
                    .outputQuality(outputQuality)
                    .toFile(new File(path.toString().concat(File.separator).concat(thumbnail)));
            log.info("thumbnail foi transferida com sucesso");
            return new ImageDto(originalFileName, thumbnail);
        } catch (Exception e){
            log.error("não foi possível transferir a imagem");
            this.removeFiles(path, originalFileName, thumbnail);
            throw new RuntimeException(e);
        }
    }

    public ImageDto replaceImage(MultipartFile multipartFile, Path path, String originalFileName, String thumbnail){
        this.removeFiles(path, originalFileName, thumbnail);
        return this.storeImage(multipartFile, path);
    }

    public void removeFiles(Path path, String originalFileName, String thumbnail){
        try{
            log.info("iniciando remoção do arquivo");
            Files.delete(new File(path.toString().concat(File.separator).concat(originalFileName)).toPath());
            Files.delete(new File(path.toString().concat(File.separator).concat(thumbnail)).toPath());
            Files.delete(new File(path.toString()).toPath());
            log.info(String.format("arquivos foram removidos com sucesso do path %s", path));
        }catch (NoSuchFileException e){
            log.error("arquivo não foi localizado !");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ImageDto validUpdateOfImage(Path path, MultipartFile multipartFile, AbstractDomainWithImage adwi){
        ImageDto imageDto;
        if(!Objects.isNull(multipartFile)){
            log.info("imagem será substituida por uma nova !");
            imageDto = this.replaceImage(
                    multipartFile,
                    path,
                    adwi.getPathOfImage(),
                    adwi.getPathOfImageThumb()
            );
        }else{
            log.info("nenhuma nova imagem foi enviada");
            imageDto = new ImageDto(adwi.getPathOfImage(), adwi.getPathOfImageThumb());
        }
        return imageDto;
    }



}
