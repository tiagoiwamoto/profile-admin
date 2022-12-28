package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CertificationUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CourseCategoryUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ExperienceUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ProfileUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ProjectUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ResumeUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ScholarityUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.SkillUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.SoftwareUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
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
class ResourceTest {

    @InjectMocks
    private CourseCategoryResource courseCategoryResource;
    @InjectMocks
    private ExperienceResource experienceResource;
    @InjectMocks
    private ProfileResource profileResource;
    @InjectMocks
    private ProjectResource projectResource;
    @InjectMocks
    private ResumeResource resumeResource;
    @InjectMocks
    private SkillResource skillResource;
    @Mock
    private CourseCategoryUsecase courseCategoryUsecase;
    @Mock
    private ExperienceUsecase experienceUsecase;
    @Mock
    private ProfileUsecase profileUsecase;
    @Mock
    private ProjectUsecase projectUsecase;
    @Mock
    private ResumeUsecase resumeUsecase;
    @Mock
    private SkillUsecase skillUsecase;
    private List<TestsFactory> testsFactories;

    @BeforeEach
    public void init(){
        this.testsFactories = new ArrayList<>();
        this.testsFactories.add(new TestsFactory(courseCategoryUsecase, courseCategoryResource, "courseCategory", CourseCategoryDto.class));
        this.testsFactories.add(new TestsFactory(experienceUsecase, experienceResource, "experience", ExperienceDto.class));
        this.testsFactories.add(new TestsFactory(profileUsecase, profileResource, "profile", ProfileDto.class));
        this.testsFactories.add(new TestsFactory(projectUsecase, projectResource, "project", ProjectDto.class));
        this.testsFactories.add(new TestsFactory(resumeUsecase, resumeResource, "resume", ResumeDto.class));
        this.testsFactories.add(new TestsFactory(skillUsecase, skillResource, "skill", SkillDto.class));
    }

    @Test
    @DisplayName(value = "Consulta registros por domínio")
    public void testRecoveryAll() {
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testRecoveryAll() para: ".concat(test.getResource().getClass().getSimpleName()));
            Mockito.when(test.getUsecase().recoveryRecords()).thenReturn(List.of(new AbstractDto()));
            var responseIndex = (ResponseEntity<List<AbstractDto>>) test.getResource().index();
            Assertions.assertEquals(HttpStatus.OK , responseIndex.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Consulta de registro pelo seu UUID")
    public void testRecoveryByUuid() {
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testRecoveryByUuid() para: ".concat(test.getResource().getClass().getSimpleName()));
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecase().recoveryRecord(Mockito.any())).thenReturn(response);
            var responseRecovery = (ResponseEntity<AbstractDto>)test.getResource().recoveryRecord(UUID.randomUUID());
            Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Criação de registro")
    public void testCreate(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testCreate() para: ".concat(test.getResource().getClass().getSimpleName()));
            var request = this.convertObjectToClass(test.getFileName().concat("-request"), test.getClassToSerialize());
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecase().createOrUpdate(Mockito.any())).thenReturn(response);
            var responseRecovery = test.getResource().create(request);
            Assertions.assertEquals(HttpStatus.CREATED , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Atualização de registro")
    public void testUpdate(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testUpdate() para: ".concat(test.getResource().getClass().getSimpleName()));
            var request = this.convertObjectToClass(test.getFileName().concat("-update-request"), test.getClassToSerialize());
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecase().createOrUpdate(Mockito.any())).thenReturn(response);
            var responseRecovery = test.getResource().update(request);
            Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Remoção de registro")
    public void testDelete(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execução testDelete() para: ".concat(test.getUsecase().getClass().getSimpleName()));
            Mockito.doNothing().when(test.getUsecase()).removeRecord(Mockito.any(UUID.class));
            var responseRecovery = test.getResource().removeRecord(UUID.randomUUID());
            Assertions.assertEquals(HttpStatus.NO_CONTENT , responseRecovery.getStatusCode());
        });
    }

    private AbstractDto convertObjectToClass(String fileName, Class clazz) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var dtoObject = (AbstractDto) mapper.readValue(file, clazz);
            return dtoObject;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

}