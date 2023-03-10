package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@ToString
public class AbstractDomainWithImage extends AbstractDomain{

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
