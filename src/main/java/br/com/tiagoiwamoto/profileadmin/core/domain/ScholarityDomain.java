package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_scholarities")
@RequiredArgsConstructor
@Getter
@Setter
public class ScholarityDomain extends AbstractDomainWithImage {

    private String schoolName;
    private String courseName;
    private String titleReceivedCourse;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate dateOfConclusion;

    public void createOrUpdate(){
        if(Objects.isNull(this.getId())){
            this.domainToSave();
        }else{
            this.domainToUpdate(this);
        }
    }

}
