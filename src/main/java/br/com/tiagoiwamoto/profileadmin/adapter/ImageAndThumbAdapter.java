package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomainWithImage;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.ImageRemoveException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.ImageStoreException;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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
            this.removeFiles(path);
            throw new ImageStoreException(e);
        }
    }

    public ImageDto replaceImage(MultipartFile multipartFile, Path path){
        this.removeFiles(path);
        return this.storeImage(multipartFile, path);
    }

    public void removeFiles(Path path){
        try{
            log.info(String.format("iniciando remoção de arquivos para o path: %s", path));
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            log.info(String.format("arquivos foram removidos com sucesso do path %s", path));
        }catch (Exception e){
            log.error(String.format("falha ao remover arquivos para o path %s", path), e);
            throw new ImageRemoveException(e);
        }
    }

    public ImageDto validUpdateOfImage(Path path, MultipartFile multipartFile, AbstractDomainWithImage domain){
        log.info(String.format("validando se uma imagem sera armazenada ou substituida para o path: %s", path));
        ImageDto imageDto;
        if(!Objects.isNull(multipartFile)){
            log.info(String.format("imagem será substituida por uma nova no path: %s", path));
            imageDto = this.replaceImage(
                    multipartFile,
                    path
            );
        }else{
            log.info("nenhuma nova imagem foi enviada");
            log.info(String.format("nenhuma nova imagem foi enviada para o path: %s", path));
            imageDto = new ImageDto(domain.getPathOfImage(), domain.getPathOfImageThumb());
        }
        return imageDto;
    }



}
