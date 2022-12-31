package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomainWithImage;
import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.ImageRemoveException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.ImageStoreException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class ImageAndThumbAdapterTest {

    @InjectMocks
    private ImageAndThumbAdapter adapter;
    private Path path;

    @Test
    @DisplayName("Grava uma imagem e sua miniatura na pasta temporaria do sistema")
    void storeImage() throws IOException {
        var multipart = new MockMultipartFile("meu-arquivo", "meu-arquivo.png", MediaType.IMAGE_PNG_VALUE, this.generateImage().toByteArray());
        Path pathToCreate = Files.createTempDirectory("tmp").toRealPath();
        this.path = Path.of(pathToCreate.toString().concat("/").concat(UUID.randomUUID().toString()));
        ImageDto imageDto = this.adapter.storeImage(multipart, this.path);
        Assertions.assertNotNull(imageDto.getPathOfImage());
        Assertions.assertNotNull(imageDto.getPathOfThumb());
        this.adapter.removeFiles(this.path);
    }

    @Test
    @DisplayName("Ao tentar gerar uma miniatura o sistema falha e lança a exception ImageStoreException")
    void storeImageException() throws IOException {
        var multipart = new MockMultipartFile("meu-arquivo", "meu-arquivo.png", MediaType.IMAGE_PNG_VALUE, UUID.randomUUID().toString().getBytes());
        Path pathToCreate = Files.createTempDirectory("tmp").toRealPath();
        this.path = Path.of(pathToCreate.toString().concat("/").concat(UUID.randomUUID().toString()));
        Assertions.assertThrows(
                ImageStoreException.class, () -> this.adapter.storeImage(multipart, path)
        );
    }

    @Test
    void replaceImage() throws IOException {
        var multipart = new MockMultipartFile("meu-arquivo", "meu-arquivo.png", MediaType.IMAGE_PNG_VALUE, this.generateImage().toByteArray());
        Path pathToCreate = Files.createTempDirectory("tmp").toRealPath();
        this.path = Path.of(pathToCreate.toString().concat("/").concat(UUID.randomUUID().toString()));
        this.adapter.storeImage(multipart, this.path);
        ImageDto imageDto = this.adapter.replaceImage(multipart, this.path);
        Assertions.assertNotNull(imageDto.getPathOfImage());
        Assertions.assertNotNull(imageDto.getPathOfThumb());
        this.adapter.removeFiles(this.path);
    }

    @Test
    void removeFiles() throws IOException {
        var multipart = new MockMultipartFile("meu-arquivo", "meu-arquivo.png", MediaType.IMAGE_PNG_VALUE, this.generateImage().toByteArray());
        Path pathToCreate = Files.createTempDirectory("tmp").toRealPath();

        this.path = Path.of(pathToCreate.toString().concat("/").concat(UUID.randomUUID().toString()));
        this.adapter.storeImage(multipart, this.path);
        this.adapter.removeFiles(this.path);
    }

    @Test
    void removeFilesException() {
        Assertions.assertThrows(
                ImageRemoveException.class, () -> this.adapter.removeFiles(this.path)
        );
    }

    @Test
    void validUpdateOfImage() throws IOException {
        var multipart = new MockMultipartFile("meu-arquivo", "meu-arquivo.png", MediaType.IMAGE_PNG_VALUE, this.generateImage().toByteArray());
        Path pathToCreate = Files.createTempDirectory("tmp").toRealPath();
        this.path = Path.of(pathToCreate.toString().concat("/").concat(UUID.randomUUID().toString()));
        ImageDto imageDto = this.adapter.storeImage(multipart, this.path);

        AbstractDomainWithImage domain = new CertificationDomain();
        domain.setPathOfImage(imageDto.getPathOfImage());
        domain.setPathOfImageThumb(imageDto.getPathOfThumb());
        ImageDto imageDtoSubstituido = this.adapter.validUpdateOfImage(this.path, multipart, domain);
        Assertions.assertNotNull(imageDtoSubstituido.getPathOfImage());
        Assertions.assertNotNull(imageDtoSubstituido.getPathOfThumb());
        this.adapter.removeFiles(this.path);

        ImageDto imageDtoSemSubstituicao = this.adapter.validUpdateOfImage(this.path, null, domain);
        Assertions.assertNotNull(imageDtoSemSubstituicao.getPathOfImage());
        Assertions.assertNotNull(imageDtoSemSubstituicao.getPathOfThumb());

    }

    private ByteArrayOutputStream generateImage(){
        try {
            BufferedImage image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            return outputStream;
        } catch (IOException e) {
            return null;
        }
    }
}