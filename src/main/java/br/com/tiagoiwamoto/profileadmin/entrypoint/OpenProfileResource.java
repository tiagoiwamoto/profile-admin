package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CertificationUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CourseCategoryUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CourseUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ExperienceUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ProfileUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ProjectUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ResumeUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ScholarityUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.SkillUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.SoftwareUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.OpenProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/open/profile")
@RequiredArgsConstructor
public class OpenProfileResource {

    private final CertificationUsecase certificationUsecase;
    private final CourseCategoryUsecase courseCategoryUsecase;
    private final CourseUsecase courseUsecase;
    private final ExperienceUsecase experienceUsecase;
    private final ProfileUsecase profileUsecase;
    private final ProjectUsecase projectUsecase;
    private final ResumeUsecase resumeUsecase;
    private final ScholarityUsecase scholarityUsecase;
    private final SkillUsecase skillUsecase;
    private final SoftwareUsecase softwareUsecase;

    @GetMapping
    public ResponseEntity<OpenProfileResponse> index(){
        var categories = this.courseCategoryUsecase.recoveryRecords();
//        var courses = new ArrayList<CourseDto>();
//        categories.forEach(category -> courses.addAll(this.courseUsecase.recoveryRecords(category.getUuid())));
        var response = OpenProfileResponse.builder()
                .skills(this.skillUsecase.recoveryRecords())
                .certifications(this.certificationUsecase.recoveryRecords())
                .coursesCategories(categories)
                .latestCourses(this.courseUsecase.recoveryLatestRecords())
                .experiences(this.experienceUsecase.recoveryRecords())
                .profiles(this.profileUsecase.recoveryRecords())
                .scholarities(this.scholarityUsecase.recoveryRecords())
                .softwares(this.softwareUsecase.recoveryRecords())
                .projects(this.projectUsecase.recoveryRecords())
                .metrics(this.courseUsecase.getMetrics())
                .resumes(this.resumeUsecase.recoveryRecords()).build();
        return ResponseEntity.ok(response);
    }

}
