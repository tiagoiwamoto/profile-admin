package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CourseUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class CourseResourceTest {

    @InjectMocks
    private CourseResource courseResource;
    @Mock
    private CourseUsecase courseUsecase;

    @Test
    @DisplayName(value = "Consulta registros pora course")
    public void testRecoveryAll() {
        try {
            log.info("Teste em execução testRecoveryAll() para: ".concat(courseResource.getClass().getSimpleName()));
            Mockito.when(courseUsecase.recoveryRecords(Mockito.any(UUID.class))).thenReturn(List.of(new CourseDto()));
            var responseIndex = (ResponseEntity<List<CourseDto>>) courseResource.index(UUID.randomUUID());
            Assertions.assertEquals(HttpStatus.OK , responseIndex.getStatusCode());
        } catch (Exception e) {
            log.error("Falha ao executar o teste:  ".concat(courseResource.getClass().getSimpleName()), e);
        }
    }

    @Test
    @DisplayName(value = "Consulta de registro pelo seu UUID")
    public void testRecoveryByUuid() {
        log.info("Teste em execução testRecoveryByUuid() para: ".concat(courseResource.getClass().getSimpleName()));
        var response = this.convertObjectToClass("course-response", CourseDto.class);
        Mockito.when(this.courseUsecase.recoveryRecord(Mockito.any())).thenReturn(response);
        var responseRecovery = (ResponseEntity<CourseDto>)this.courseResource.recoveryRecord(UUID.randomUUID());
        Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
    }

    @Test
    @DisplayName(value = "Criação de registro")
    public void testCreate(){
        log.info("Teste em execução testCreate() para: ".concat(courseResource.getClass().getSimpleName()));
        var request = this.convertObjectToClass("course-request", CourseDto.class);
        var response = this.convertObjectToClass("course-response", CourseDto.class);
        Mockito.when(this.courseUsecase.createOrUpdate(Mockito.any(), Mockito.any())).thenReturn(response);
        var responseRecovery = this.courseResource.create(request, new MockMultipartFile("meu-arquivo.png", "".getBytes()));
        Assertions.assertEquals(HttpStatus.CREATED , responseRecovery.getStatusCode());
    }

    @Test
    @DisplayName(value = "Atualização de registro")
    public void testUpdate(){
        log.info("Teste em execução testUpdate() para: ".concat(courseResource.getClass().getSimpleName()));
        var request = this.convertObjectToClass("course-update-request", CourseDto.class);
        var response = this.convertObjectToClass("course-update-response", CourseDto.class);
        Mockito.when(this.courseUsecase.createOrUpdate(Mockito.any(), Mockito.any())).thenReturn(response);
        var responseRecovery = this.courseResource.update(request, new MockMultipartFile("meu-arquivo.png", "".getBytes()));
        Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
    }

    @Test
    @DisplayName(value = "Remoção de registro")
    public void testDelete(){
        log.info("Teste em execução testDelete() para: ".concat(courseResource.getClass().getSimpleName()));
        Mockito.doNothing().when(this.courseUsecase).removeRecord(Mockito.any(UUID.class));
        var responseRecovery = this.courseResource.removeRecord(UUID.randomUUID());
        Assertions.assertEquals(HttpStatus.NO_CONTENT , responseRecovery.getStatusCode());
    }

    private CourseDto convertObjectToClass(String fileName, Class clazz) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var dtoObject = (CourseDto) mapper.readValue(file, clazz);
            return dtoObject;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

}