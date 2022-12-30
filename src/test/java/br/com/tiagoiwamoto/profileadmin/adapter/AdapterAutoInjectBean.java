package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.CertificationAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ExperienceAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProfileAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProjectAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ResumeAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ScholarityAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.SkillAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.SoftwareAdapter;
import br.com.tiagoiwamoto.profileadmin.core.repository.CertificationRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.CourseCategoryRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.CourseRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.ExperienceRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.ProfileRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.ProjectRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.ResumeRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.ScholarityRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.SkillRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.SoftwareRepository;
import br.com.tiagoiwamoto.profileadmin.entrypoint.impl.TestsFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@Slf4j
public class AdapterAutoInjectBean {

    protected List<TestsFactory> testsFactories;
    @InjectMocks
    protected CertificationAdapter certificationAdapter;
    @Mock
    protected CertificationRepository certificationRepository;
    @InjectMocks
    protected CourseCategoryAdapter courseCategoryAdapter;
    @Mock
    protected CourseCategoryRepository courseCategoryRepository;
    @InjectMocks
    protected ExperienceAdapter experienceAdapter;
    @Mock
    protected ExperienceRepository experienceRepository;
    @InjectMocks
    protected ProjectAdapter projectAdapter;
    @Mock
    protected ProjectRepository projectRepository;
    @InjectMocks
    protected ResumeAdapter resumeAdapter;
    @Mock
    protected ResumeRepository resumeRepository;
    @InjectMocks
    protected ScholarityAdapter scholarityAdapter;
    @Mock
    protected ScholarityRepository scholarityRepository;
    @InjectMocks
    protected SkillAdapter skillAdapter;
    @Mock
    protected SkillRepository skillRepository;
    @InjectMocks
    protected SoftwareAdapter softwareAdapter;
    @Mock
    protected SoftwareRepository softwareRepository;
    @InjectMocks
    protected CourseAdapter courseAdapter;
    @Mock
    protected CourseRepository courseRepository;
    @InjectMocks
    protected ProfileAdapter profileAdapter;
    @Mock
    protected ProfileRepository profileRepository;

}
