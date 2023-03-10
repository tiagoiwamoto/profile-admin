package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class ResourceTest extends ResourceAutoInjectBean{

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
    @DisplayName(value = "Consulta registros por dom??nio")
    public void testRecoveryAll() {
        this.testsFactories.forEach(test -> {
            log.info("Teste em execu????o testRecoveryAll() para: ".concat(test.getResource().getClass().getSimpleName()));
            Mockito.when(test.getUsecase().recoveryRecords()).thenReturn(List.of(new AbstractDto()));
            var responseIndex = (ResponseEntity<List<AbstractDto>>) test.getResource().index();
            Assertions.assertEquals(HttpStatus.OK , responseIndex.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Consulta de registro pelo seu UUID")
    public void testRecoveryByUuid() {
        this.testsFactories.forEach(test -> {
            log.info("Teste em execu????o testRecoveryByUuid() para: ".concat(test.getResource().getClass().getSimpleName()));
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecase().recoveryRecord(Mockito.any())).thenReturn(response);
            var responseRecovery = (ResponseEntity<AbstractDto>)test.getResource().recoveryRecord(UUID.randomUUID());
            Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Cria????o de registro")
    public void testCreate(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execu????o testCreate() para: ".concat(test.getResource().getClass().getSimpleName()));
            var request = this.convertObjectToClass(test.getFileName().concat("-request"), test.getClassToSerialize());
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecase().createOrUpdate(Mockito.any())).thenReturn(response);
            var responseRecovery = test.getResource().create(request);
            Assertions.assertEquals(HttpStatus.CREATED , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Atualiza????o de registro")
    public void testUpdate(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execu????o testUpdate() para: ".concat(test.getResource().getClass().getSimpleName()));
            var request = this.convertObjectToClass(test.getFileName().concat("-update-request"), test.getClassToSerialize());
            var response = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Mockito.when(test.getUsecase().createOrUpdate(Mockito.any())).thenReturn(response);
            var responseRecovery = test.getResource().update(request);
            Assertions.assertEquals(HttpStatus.OK , responseRecovery.getStatusCode());
        });
    }

    @Test
    @DisplayName(value = "Remo????o de registro")
    public void testDelete(){
        this.testsFactories.forEach(test -> {
            log.info("Teste em execu????o testDelete() para: ".concat(test.getUsecase().getClass().getSimpleName()));
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