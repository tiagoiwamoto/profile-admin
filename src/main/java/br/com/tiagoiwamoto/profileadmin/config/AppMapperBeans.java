package br.com.tiagoiwamoto.profileadmin.config;

import br.com.tiagoiwamoto.profileadmin.core.mapper.CertificationMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseCategoryMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ExperienceMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProfileMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProjectMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ResumeMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ScholarityMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SkillMapper;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SoftwareMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppMapperBeans {

    @Bean
    public ProfileMapper getProfileMapper(){
        return new ProfileMapper();
    }

    @Bean
    public ScholarityMapper getScholarityMapper(){
        return new ScholarityMapper();
    }

    @Bean
    public ResumeMapper getResumeMapper(){
        return new ResumeMapper();
    }

    @Bean
    public ExperienceMapper getExperienceMapper(){
        return new ExperienceMapper();
    }

    @Bean
    public CertificationMapper getCertificationMapper(){
        return new CertificationMapper();
    }

    @Bean
    public SkillMapper getSkillMapper(){
        return new SkillMapper();
    }

    @Bean
    public ProjectMapper getProjectMapper(){
        return new ProjectMapper();
    }

    @Bean
    public CourseCategoryMapper getCourseCategoryMapper(){
        return new CourseCategoryMapper();
    }

    @Bean
    public CourseMapper getCourseMapper(){
        return new CourseMapper(this.getCourseCategoryMapper());
    }

    @Bean
    public SoftwareMapper getSoftwareMapper(){
        return new SoftwareMapper();
    }

}
