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
import java.util.Objects;

@Entity
@Table(name = "tbl_courses")
@RequiredArgsConstructor
@Getter
@Setter
public class CourseDomain extends AbstractDomainWithImage {

    private String name;
    private String school;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private CourseCategoryDomain courseCategory;

    public void createOrUpdate(){
        if(Objects.isNull(this.getId())){
            this.domainToSave();
        }else{
            this.domainToUpdate(this);
        }
    }

}
