package br.com.tiagoiwamoto.profileadmin.adapter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Slf4j
class ImageAndThumbAdapterTest {

    @InjectMocks
    private ImageAndThumbAdapter adapter;

    @Test
    void storeImage() throws IOException {
        BufferedImage result = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(result, "png", baos);
        byte[] bytes = baos.toByteArray();
        var multipart = new MockMultipartFile("meu-arquivo", "meu-arquivo.png", MediaType.IMAGE_PNG_VALUE, bytes);
        Path pathToCreate = Files.createTempDirectory("tmp").toRealPath();
        Path path = Path.of(pathToCreate.toString().concat("/").concat(UUID.randomUUID().toString()));
        this.adapter.storeImage(multipart, path);
    }

    @Test
    void replaceImage() {
    }

    @Test
    void removeFiles() {
    }

    @Test
    void validUpdateOfImage() {
    }
}