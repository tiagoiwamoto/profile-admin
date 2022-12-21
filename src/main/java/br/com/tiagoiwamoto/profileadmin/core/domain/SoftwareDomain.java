package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_softwares")
@RequiredArgsConstructor
@Getter
@Setter
public class SoftwareDomain extends AbstractDomain{

    private String name;
    @Column(length = 9999)
    private String description;
    private String url;
    private String urlMirror;
    private String pathOfImage;
    private String pathOfImageThumb;

    @Override
    public void domainToSave() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }
}
