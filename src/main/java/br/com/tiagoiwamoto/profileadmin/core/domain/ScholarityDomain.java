package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_scholarities")
@RequiredArgsConstructor
@Getter
@Setter
public class ScholarityDomain extends AbstractDomain{

    private String schoolName;
    private String courseName;
    private String titleReceivedCourse;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate dateOfConclusion;

    private String pathOfImage;
    private String pathOfImageThumb;

    public void domainToSave() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }
}
