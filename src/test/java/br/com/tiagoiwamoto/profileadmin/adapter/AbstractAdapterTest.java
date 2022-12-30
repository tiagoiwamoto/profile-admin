package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ExperienceDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ProjectDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.SkillDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRecoveryException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRemoveException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordSaveException;
import br.com.tiagoiwamoto.profileadmin.entrypoint.impl.TestsFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@Slf4j
class AbstractAdapterTest extends AdapterAutoInjectBean{

    @BeforeEach
    public void init(){
        this.testsFactories = new ArrayList<>();
        this.testsFactories.add(new TestsFactory(this.certificationAdapter, this.certificationRepository, CertificationDomain.class, "certification"));
        this.testsFactories.add(new TestsFactory(this.courseCategoryAdapter, this.courseCategoryRepository, CourseCategoryDomain.class, "courseCategory"));
        this.testsFactories.add(new TestsFactory(this.experienceAdapter, this.experienceRepository, ExperienceDomain.class, "experience"));
        this.testsFactories.add(new TestsFactory(this.projectAdapter, this.projectRepository, ProjectDomain.class, "project"));
        this.testsFactories.add(new TestsFactory(this.resumeAdapter, this.resumeRepository, ResumeDomain.class, "resume"));
        this.testsFactories.add(new TestsFactory(this.scholarityAdapter, this.scholarityRepository, ScholarityDomain.class, "scholarity"));
        this.testsFactories.add(new TestsFactory(this.skillAdapter, this.skillRepository, SkillDomain.class, "skill"));
        this.testsFactories.add(new TestsFactory(this.softwareAdapter, this.softwareRepository, SoftwareDomain.class, "software"));
        this.testsFactories.add(new TestsFactory(this.courseAdapter, this.courseRepository, CourseDomain.class, "course"));
        this.testsFactories.add(new TestsFactory(this.profileAdapter, this.profileRepository, ProfileDomain.class, "profile"));

    }
    @Test
    @DisplayName("Busca lista de registros e simula erro de conexão com o banco de dados")
    public void testAll(){
        this.testsFactories.stream()
                .filter(a -> !Objects.equals(a.getFileName(), "course"))
                .collect(Collectors.toList())
                .forEach(test -> {
                    var record = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerializeDomain());
                    Mockito.when(test.getRepository().findAll())
                            .thenReturn(List.of(record))
                            .thenThrow(new RuntimeException("Failed to connect with database"));
                    var result = test.getIAdapter().all();
                    Assertions.assertNotNull(result);

                    Assertions.assertThrows(
                            RecordRecoveryException.class, () -> test.getIAdapter().all()
                    );
        });
    }

    @Test
    @DisplayName("Salva um registro no banco de dados e simula erro de conexão")
    public void save(){
        this.testsFactories.stream()
                .filter(a -> !Objects.equals(a.getFileName(), "profile"))
                .collect(Collectors.toList())
                .forEach(test -> {
                    var record = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerializeDomain());
                    Mockito.when(test.getRepository().save(Mockito.any()))
                            .thenReturn(record)
                            .thenThrow(new RuntimeException("Failed to connect with database"));
                    var result = test.getIAdapter().save(record);
                    Assertions.assertNotNull(result);

                    Assertions.assertThrows(
                            RecordSaveException.class, () -> test.getIAdapter().save(record)
                    );
        });
    }

    @Test
    @DisplayName("Recupera um registro no banco de dados e simula erro de conexão")
    public void testRecoveryByUuid(){
        this.testsFactories.forEach(test -> {
            var record = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerializeDomain());
            Mockito.when(test.getRepository().findByUuid(Mockito.any(UUID.class)))
                    .thenReturn(Optional.of(record))
                    .thenThrow(new RuntimeException("Failed to connect with database"));
            var result = test.getIAdapter().recoveryByUuid(UUID.randomUUID());
            Assertions.assertNotNull(result);

            Assertions.assertThrows(
                    RecordRecoveryException.class, () -> test.getIAdapter().recoveryByUuid(UUID.randomUUID())
            );
        });
    }

    @Test
    @DisplayName("Remove um registro no banco de dados e simula erro de conexão")
    public void testDelete(){
        this.testsFactories.forEach(test -> {
            var record = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerializeDomain());
            Mockito.when(test.getRepository().findByUuid(Mockito.any(UUID.class)))
                    .thenReturn(Optional.of(record))
                    .thenThrow(new RuntimeException("Failed to connect with database"));
            Mockito.doNothing().when(test.getRepository()).delete(Mockito.any());
            test.getIAdapter().delete(UUID.randomUUID());

            Assertions.assertThrows(
                    RecordRemoveException.class, () -> test.getIAdapter().delete(UUID.randomUUID())
            );
        });
    }

    @Test
    @DisplayName("Busca lista de cursos e simula erro de conexão com o banco de dados")
    public void testCourseAll(){
        var record = this.convertObjectToClass("course-response", CourseDomain.class);
        Mockito.when(this.courseRepository.findAllByCourseCategory(Mockito.any()))
                .thenReturn((List) List.of(record))
                .thenThrow(new RuntimeException("Failed to connect with database"));
        var result = this.courseAdapter.all(this.getCourseCategory());
        Assertions.assertNotNull(result);

        Assertions.assertThrows(
                RecordRecoveryException.class, () -> this.courseAdapter.all(this.getCourseCategory())
        );
    }

    @Test
    @DisplayName("")
    public void testProfileSave(){
        var record = this.convertObjectToClass("profile-response", ProfileDomain.class);
        Mockito.when(this.profileRepository.findAll()).thenReturn((List) List.of(record));
        Mockito.when(this.profileRepository.saveAll(Mockito.any())).thenReturn((List) List.of(record));
        Mockito.when(this.profileRepository.save(Mockito.any()))
                .thenReturn(record)
                .thenThrow(new RuntimeException("Failed to connect with database"));
        var result = this.profileAdapter.save((ProfileDomain) record);
        Assertions.assertNotNull(result);

        Assertions.assertThrows(
                RecordSaveException.class, () -> this.profileAdapter.save((ProfileDomain) record)
        );
    }

    private CourseCategoryDomain getCourseCategory(){
        var category = new CourseCategoryDomain();
        category.setUuid(UUID.randomUUID());
        category.setId(1000L);
        category.setName("name");
        category.setDescription("description");
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }

    private AbstractDomain convertObjectToClass(String fileName, Class clazz) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var domain = (AbstractDomain) mapper.readValue(file, clazz);
            return domain;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

}