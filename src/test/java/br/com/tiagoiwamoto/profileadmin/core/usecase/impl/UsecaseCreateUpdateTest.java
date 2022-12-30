package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ExperienceDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ProjectDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.SkillDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class UsecaseCreateUpdateTest extends UsecaseAutoInjectBean {

    @BeforeEach
    public void init(){
        this.testsFactories = new ArrayList<>();
        this.testsFactories.add(new TestsFactory(courseCategoryUsecase, courseCategoryAdapter, courseCategoryMapper, "courseCategory", CourseCategoryDto.class, CourseCategoryDomain.class));
        this.testsFactories.add(new TestsFactory(experienceUsecase, experienceAdapter, experienceMapper, "experience", ExperienceDto.class, ExperienceDomain.class));
        this.testsFactories.add(new TestsFactory(profileUsecase, profileAdapter, profileMapper, "profile", ProfileDto.class, ProfileDomain.class));
        this.testsFactories.add(new TestsFactory(projectUsecase, projectAdapter, projectMapper, "project", ProjectDto.class, ProjectDomain.class));
        this.testsFactories.add(new TestsFactory(resumeUsecase, resumeAdapter, resumeMapper, "resume", ResumeDto.class, ResumeDomain.class));
        this.testsFactories.add(new TestsFactory(skillUsecase, skillAdapter, skillMapper, "skill", SkillDto.class, SkillDomain.class));
    }

    @Test
    @DisplayName("Testar a funcionalidade de cadastro de novo registro")
    public void testCreate(){
        this.testsFactories.forEach(test -> {
            var request = this.convertObjectToClass(test.getFileName().concat("-request"), test.getClassToSerialize());
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(request, test.getClassToSerializeDomain());
            Mockito.when(test.getIAdapter().save(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIMapper().toDomain(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIMapper().toDto(Mockito.any())).thenReturn(responseDto);
            Assertions.assertNotNull(test.getUsecase().createOrUpdate(request));
        });

    }
    @Test
    @DisplayName("Testar a funcionalidade de atualização de registro")
    public void testUpdate(){
        this.testsFactories.forEach(test -> {
            var request = this.convertObjectToClass(test.getFileName().concat("-update-request"), test.getClassToSerialize());
            var responseDto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            var response = this.toDomain(request, test.getClassToSerializeDomain());
            Mockito.when(test.getIAdapter().save(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIAdapter().recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
            Mockito.when(test.getIMapper().toDomain(Mockito.any())).thenReturn(response);
            Mockito.when(test.getIMapper().toDto(Mockito.any())).thenReturn(responseDto);
            Assertions.assertNotNull(test.getUsecase().createOrUpdate(request));
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
            Assertions.assertNotNull(test.getUsecase().recoveryRecords());
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
            Assertions.assertNotNull(test.getUsecase().recoveryRecord(UUID.randomUUID()));
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
            test.getUsecase().removeRecord(UUID.randomUUID());
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

    private AbstractDomain toDomain(AbstractDto dto, Class clazz){
        try {
            var abstractDomain = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, abstractDomain);
            return (AbstractDomain) abstractDomain;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}