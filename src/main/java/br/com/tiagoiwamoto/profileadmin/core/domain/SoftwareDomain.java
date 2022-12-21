package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tbl_softwares")
@RequiredArgsConstructor
@Getter
@Setter
public class SoftwareDomain extends AbstractDomainWithImage {

    private String name;
    @Column(length = 9999)
    private String description;
    private String url;
    private String urlMirror;

    public void createOrUpdate(){
        if(Objects.isNull(this.getId())){
            this.domainToSave();
        }else{
            this.domainToUpdate(this);
        }
    }

}
