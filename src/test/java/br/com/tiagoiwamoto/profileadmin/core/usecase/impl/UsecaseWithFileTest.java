package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomainWithImage;
import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SoftwareDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.impl.TestsFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class UsecaseWithFileTest extends UsecaseAutoInjectBean {

    @BeforeEach
    public void init(){
        this.testsFactories = new ArrayList<>();
        this.testsFactories.add(new TestsFactory(certificationUsecase, certificationAdapter, certificationMapper, "certification", CertificationDto.class, CertificationDomain.class));
        this.testsFactories.add(new TestsFactory(scholarityUsecase, scholarityAdapter, scholarityMapper, "scholarity", ScholarityDto.class, ScholarityDomain.class));
        this.testsFactories.add(new TestsFactory(softwareUsecase, softwareAdapter, softwareMapper, "software", SoftwareDto.class, SoftwareDomain.class));
    }

    @Test
    @DisplayName("Testar a funcionalidade de cadastro de novo registro")
    public void testCreate(){
        this.testsFactories.forEach(test -> {
            var request = this.convertObjectToClass(test.getFileName().concat("-request"), test.getClassToSerialize());
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(request, test.getClassToSerializeDomain());
            var multipart = new MockMultipartFile("meu-arquivo.png", "".getBytes());
            Mockito.when(this.imageAndThumbAdapter.storeImage(Mockito.any(), Mockito.any()))
                    .thenReturn(new ImageDto("/image", "/thumb"));
            Mockito.when(test.getIAdapter().save(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIMapper().toDomain(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIMapper().toDto(Mockito.any())).thenReturn(responseDto);
            Assertions.assertNotNull(test.getUsecaseWithFile().createOrUpdate(request, multipart));
        });

    }
    @Test
    @DisplayName("Testar a funcionalidade de atualização de registro")
    public void testUpdate(){
        this.testsFactories.forEach(test -> {
            var request = this.convertObjectToClass(test.getFileName().concat("-update-request"), test.getClassToSerialize());
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(request, test.getClassToSerializeDomain());
            var multipart = new MockMultipartFile("meu-arquivo.png", "".getBytes());
            Mockito.when(this.imageAndThumbAdapter.validUpdateOfImage(Mockito.any(), Mockito.any(), Mockito.any()))
                    .thenReturn(new ImageDto("/image", "/thumb"));
            Mockito.when(test.getIAdapter().save(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIAdapter().recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
            Mockito.when(test.getIMapper().toDomain(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIMapper().toDto(Mockito.any())).thenReturn(responseDto);
            Assertions.assertNotNull(test.getUsecaseWithFile().createOrUpdate(request, multipart));
        });

    }

    @Test
    @DisplayName("Testar funcionalidade de recuperar todos os registros de um domínio")
    public void testRecoveryRecords(){
        this.testsFactories.forEach(test -> {
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(responseDto, test.getClassToSerializeDomain());
            Mockito.when(test.getIAdapter().all()).thenReturn(List.of(response));
            Mockito.when(test.getIMapper().toDto(Mockito.any())).thenReturn(responseDto);
            Assertions.assertNotNull(test.getUsecaseWithFile().recoveryRecords());
        });
    }

    @Test
    @DisplayName("Testar funcionalidade de recuperar um registro por UUID")
    public void testRecoveryRecord(){
        this.testsFactories.forEach(test -> {
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(responseDto, test.getClassToSerializeDomain());
            Mockito.when(test.getIAdapter().recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
            Mockito.when(test.getIMapper().toDto(Mockito.any())).thenReturn(responseDto);
            Assertions.assertNotNull(test.getUsecaseWithFile().recoveryRecord(UUID.randomUUID()));
        });
    }

    @Test
    @DisplayName("Testar funcionalidade de remover um registro por UUID")
    public void testRemoveRecord(){
        this.testsFactories.forEach(test -> {
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(responseDto, test.getClassToSerializeDomain());
            Mockito.when(test.getIAdapter().recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
            Mockito.doNothing().when(test.getIAdapter()).delete(Mockito.any(UUID.class));
            Mockito.doNothing().when(this.imageAndThumbAdapter).removeFiles(Mockito.any());
            test.getUsecaseWithFile().removeRecord(UUID.randomUUID());
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

    private AbstractDomainWithImage toDomain(AbstractDtoWithImage dto, Class clazz){
        try {
            var abstractDomain = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, abstractDomain);
            return (AbstractDomainWithImage) abstractDomain;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}