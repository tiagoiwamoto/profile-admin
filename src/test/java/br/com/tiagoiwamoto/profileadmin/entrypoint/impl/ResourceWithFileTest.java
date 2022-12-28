package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CertificationUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ScholarityUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.SoftwareUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SoftwareDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class ResourceWithFileTest {

    @InjectMocks
    private CertificationResource certificationResource;
    @InjectMocks
    private ScholarityResource scholarityResource;
    @InjectMocks
    private SoftwareResource softwareResource;
    @Mock
    private CertificationUsecase certificationUsecase;
    @Mock
    private ScholarityUsecase scholarityUsecase;
    @Mock
    private SoftwareUsecase softwareUsecase;
    private List<TestsFactory> testsFactories;

    @BeforeEach
    public void init(){
        this.testsFactories = new ArrayList<>();
        this.testsFactories.add(new TestsFactory(certificationUsecase, certificationResource, "certification", CertificationDto.class));
        this.testsFactories.add(new TestsFactory(scholarityUsecase, scholarityResource, "scholarity", ScholarityDto.class));
        this.testsFactories.add(new TestsFactory(softwareUsecase, softwareResource, "software", SoftwareDto.class));
    }

    @Test
    @DisplayName(value = "Consulta registros por domínio")
    public void testRecoveryAll() {
        this.testsFactories.forEach(test -> {
            try {
                log.info("Teste em execução testRecoveryAll() para: ".concat(test.getResourceWithFile().getClass().getSimpleName()));
                Mockito.when(test.getUsecaseWithFile().recoveryRecords()).thenReturn(List.of(new AbstractDtoWithImage()));
                var responseIndex = (ResponseEntity<List<AbstractDtoWithImage>>) test.getResourceWithFile().index();
                Assertions.assertEquals(HttpStatus.OK , responseIndex.getStatusCode());
            } catch (Exception e) {
                log.error("Falha ao executar o teste:  ".concat(test.getResource().getClass().getSimpleName()), e);
            }
        });
    }

    @Test
    @DisplayName(value = "Consulta de registro pelo seu UUID")
    public void testRecoveryByUuid() {
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testRecoveryByUuid() para: ".concat(test.getResourceWithFile().getClass().getSimpleName()));
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecaseWithFile().recoveryRecord(Mockito.any())).thenReturn(response);
            var responseRecovery = (ResponseEntity<AbstractDtoWithImage>)test.getResourceWithFile().recoveryRecord(UUID.randomUUID());
            Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Criação de registro")
    public void testCreate(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testCreate() para: ".concat(test.getUsecaseWithFile().getClass().getSimpleName()));
            var request = this.convertObjectToClass(test.getFileName().concat("-request"), test.getClassToSerialize());
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecaseWithFile().createOrUpdate(Mockito.any(), Mockito.any())).thenReturn(response);
            var responseRecovery = test.getResourceWithFile().create(request, new MockMultipartFile("meu-arquivo.png", "".getBytes()));
            Assertions.assertEquals(HttpStatus.CREATED , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Atualização de registro")
    public void testUpdate(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testUpdate() para: ".concat(test.getUsecaseWithFile().getClass().getSimpleName()));
            var request = this.convertObjectToClass(test.getFileName().concat("-update-request"), test.getClassToSerialize());
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecaseWithFile().createOrUpdate(Mockito.any(), Mockito.any())).thenReturn(response);
            var responseRecovery = test.getResourceWithFile().update(request, new MockMultipartFile("meu-arquivo.png", "".getBytes()));
            Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Remoção de registro")
    public void testDelete(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testDelete() para: ".concat(test.getUsecaseWithFile().getClass().getSimpleName()));
            Mockito.doNothing().when(test.getUsecaseWithFile()).removeRecord(Mockito.any(UUID.class));
            var responseRecovery = test.getResourceWithFile().removeRecord(UUID.randomUUID());
            Assertions.assertEquals(HttpStatus.NO_CONTENT , responseRecovery.getStatusCode());
        });
    }

    private AbstractDtoWithImage convertObjectToClass(String fileName, Class clazz) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var dtoObject = (AbstractDtoWithImage) mapper.readValue(file, clazz);
            return dtoObject;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

}