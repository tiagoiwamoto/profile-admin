package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CertificationAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ExperienceAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProfileAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProjectAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ResumeAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ScholarityAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.SkillAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.SoftwareAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CertificationMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseCategoryMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ExperienceMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProfileMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProjectMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ResumeMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ScholarityMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SkillMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SoftwareMapper;
import br.com.tiagoiwamoto.profileadmin.entrypoint.impl.TestsFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public abstract class UsecaseAutoInjectBean {

    @InjectMocks
    protected CertificationUsecase certificationUsecase;
    @Mock
    protected CertificationMapper certificationMapper;
    @Mock
    protected CertificationAdapter certificationAdapter;

    @InjectMocks
    protected ScholarityUsecase scholarityUsecase;
    @Mock
    protected ScholarityMapper scholarityMapper;
    @Mock
    protected ScholarityAdapter scholarityAdapter;

    @InjectMocks
    protected SoftwareUsecase softwareUsecase;
    @Mock
    protected SoftwareMapper softwareMapper;
    @Mock
    protected SoftwareAdapter softwareAdapter;
    @InjectMocks
    protected CourseCategoryUsecase courseCategoryUsecase;
    @Mock
    protected CourseCategoryMapper courseCategoryMapper;
    @Mock
    protected CourseCategoryAdapter courseCategoryAdapter;

    @InjectMocks
    protected ExperienceUsecase experienceUsecase;
    @Mock
    protected ExperienceMapper experienceMapper;
    @Mock
    protected ExperienceAdapter experienceAdapter;

    @InjectMocks
    protected ProfileUsecase profileUsecase;
    @Mock
    protected ProfileMapper profileMapper;
    @Mock
    protected ProfileAdapter profileAdapter;

    @InjectMocks
    protected ProjectUsecase projectUsecase;
    @Mock
    protected ProjectMapper projectMapper;
    @Mock
    protected ProjectAdapter projectAdapter;


    @InjectMocks
    protected ResumeUsecase resumeUsecase;
    @Mock
    protected ResumeMapper resumeMapper;
    @Mock
    protected ResumeAdapter resumeAdapter;

    @InjectMocks
    protected SkillUsecase skillUsecase;
    @Mock
    protected SkillMapper skillMapper;
    @Mock
    protected SkillAdapter skillAdapter;

    @Mock
    protected ImageAndThumbAdapter imageAndThumbAdapter;
    protected List<TestsFactory> testsFactories;
}
