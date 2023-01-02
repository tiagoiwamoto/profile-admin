package br.com.tiagoiwamoto.profileadmin.core.mapper;

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
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SoftwareDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.impl.TestsFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@Slf4j
class MapperTest extends MapperAutoInjectBean{

    @BeforeEach
    public void init(){
        this.testsFactories = new ArrayList<>();
        this.testsFactories.add(new TestsFactory(this.certificationMapper, CertificationDto.class, CertificationDomain.class, "certification"));
        this.testsFactories.add(new TestsFactory(this.courseCategoryMapper, CourseCategoryDto.class, CourseCategoryDomain.class, "courseCategory"));
        this.testsFactories.add(new TestsFactory(this.experienceMapper, ExperienceDto.class, ExperienceDomain.class, "experience"));
        this.testsFactories.add(new TestsFactory(this.profileMapper, ProfileDto.class, ProfileDomain.class, "profile"));
        this.testsFactories.add(new TestsFactory(this.projectMapper, ProjectDto.class, ProjectDomain.class, "project"));
        this.testsFactories.add(new TestsFactory(this.resumeMapper, ResumeDto.class, ResumeDomain.class, "resume"));
        this.testsFactories.add(new TestsFactory(this.scholarityMapper, ScholarityDto.class, ScholarityDomain.class, "scholarity"));
        this.testsFactories.add(new TestsFactory(this.skillMapper, SkillDto.class, SkillDomain.class, "skill"));
        this.testsFactories.add(new TestsFactory(this.softwareMapper, SoftwareDto.class, SoftwareDomain.class, "software"));
        this.testsFactories.add(new TestsFactory(this.courseMapper, CourseDto.class, CourseDomain.class, "course"));
    }

    @Test
    void toDomain() {
        this.testsFactories.forEach(test -> {
            var dto = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerialize());
            Assertions.assertNotNull(test.getIMapper().toDomain(dto));
        });
    }

    @Test
    void toDto() {
        this.testsFactories.stream()
                .filter(a -> !Objects.equals(a.getFileName(), "course"))
                .collect(Collectors.toList()).forEach(test -> {
                    var domain = this.convertObjectToClass(test.getFileName().concat("-response"), test.getClassToSerializeDomain());
                    Assertions.assertNotNull(test.getIMapper().toDto(domain));
        });
    }

    private Object convertObjectToClass(String fileName, Class clazz) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var object = mapper.readValue(file, clazz);
            return object;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

}