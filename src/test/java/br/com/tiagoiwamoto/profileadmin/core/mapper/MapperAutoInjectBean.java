package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.entrypoint.impl.TestsFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class MapperAutoInjectBean {

    @InjectMocks
    protected CertificationMapper certificationMapper;
    @InjectMocks
    protected CourseCategoryMapper courseCategoryMapper;
    @InjectMocks
    protected CourseMapper courseMapper;
    @InjectMocks
    protected ExperienceMapper experienceMapper;
    @InjectMocks
    protected ProfileMapper profileMapper;
    @InjectMocks
    protected ProjectMapper projectMapper;
    @InjectMocks
    protected ResumeMapper resumeMapper;
    @InjectMocks
    protected ScholarityMapper scholarityMapper;
    @InjectMocks
    protected SkillMapper skillMapper;
    @InjectMocks
    protected SoftwareMapper softwareMapper;
    protected List<TestsFactory> testsFactories;

}
