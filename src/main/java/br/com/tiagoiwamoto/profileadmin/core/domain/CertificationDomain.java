package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_certifications")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CertificationDomain extends AbstractDomain {

    private String name;
    private LocalDate earnDate;
    private String validateUrl;
    private String pathOfImage;
    private String pathOfImageThumb;

    @Override
    public void domainToSave() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void createOrUpdate(){
        if(Objects.isNull(this.getId())){
            this.domainToSave();
        }else{
            this.domainToUpdate(this);
        }
    }

}
