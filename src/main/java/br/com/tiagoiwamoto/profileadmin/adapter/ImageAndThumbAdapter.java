package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.UUID;

@Service
@Slf4j
public class ImageAndThumbAdapter {

    private final Double outputQuality = 0.7;
    private final Integer maxHeight = 200;

    public ImageDto storeImage(MultipartFile multipartFile, Path path){
        String fileName = UUID.randomUUID().toString();
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String originalFileName = fileName.concat(".").concat(fileExtension);
        String thumbnail = fileName.concat("_th").concat(".").concat(fileExtension);
        try{
            if(!path.toFile().exists()){
                path.toFile().mkdirs();
            }
            Files.copy(multipartFile.getInputStream(), path.resolve(originalFileName));
            Thumbnails.of(new File(path.toString().concat(File.separator).concat(originalFileName)))
                    .height(maxHeight)
                    .outputQuality(outputQuality)
                    .toFile(new File(path.toString().concat(File.separator).concat(thumbnail)));
            return new ImageDto(originalFileName, thumbnail);
        } catch (Exception e){
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
            Files.delete(new File(path.toString().concat(File.separator).concat(originalFileName)).toPath());
            Files.delete(new File(path.toString().concat(File.separator).concat(thumbnail)).toPath());
            Files.delete(new File(path.toString()).toPath());
        }catch (NoSuchFileException e){
            log.error("arquivo não foi localizado !");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



}
