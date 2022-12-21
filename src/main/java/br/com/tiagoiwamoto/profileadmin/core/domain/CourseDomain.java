package br.com.tiagoiwamoto.profileadmin.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_courses")
@RequiredArgsConstructor
@Getter
@Setter
public class CourseDomain extends AbstractDomain{

    private String name;
    private String school;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pathOfImage;
    private String pathOfImageThumb;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private CourseCategoryDomain courseCategory;

    @Override
    public void domainToSave() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }
}
