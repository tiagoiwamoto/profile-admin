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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ResourceAutoInjectBean {

    @InjectMocks
    protected CertificationResource certificationResource;
    @InjectMocks
    protected ScholarityResource scholarityResource;
    @InjectMocks
    protected SoftwareResource softwareResource;
    @Mock
    protected CertificationUsecase certificationUsecase;
    @Mock
    protected ScholarityUsecase scholarityUsecase;
    @Mock
    protected SoftwareUsecase softwareUsecase;
    @InjectMocks
    protected CourseCategoryResource courseCategoryResource;
    @InjectMocks
    protected ExperienceResource experienceResource;
    @InjectMocks
    protected ProfileResource profileResource;
    @InjectMocks
    protected ProjectResource projectResource;
    @InjectMocks
    protected ResumeResource resumeResource;
    @InjectMocks
    protected SkillResource skillResource;
    @Mock
    protected CourseCategoryUsecase courseCategoryUsecase;
    @Mock
    protected ExperienceUsecase experienceUsecase;
    @Mock
    protected ProfileUsecase profileUsecase;
    @Mock
    protected ProjectUsecase projectUsecase;
    @Mock
    protected ResumeUsecase resumeUsecase;
    @Mock
    protected SkillUsecase skillUsecase;
    protected List<TestsFactory> testsFactories;
    
}
