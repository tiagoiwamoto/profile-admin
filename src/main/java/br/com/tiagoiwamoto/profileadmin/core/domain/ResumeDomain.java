package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_resumes")
@RequiredArgsConstructor
@Getter
@Setter
public class ResumeDomain extends AbstractDomain{

    private String title;
    private String description;
    private String language;
    private String type;
    private String url;
    @Column(length = 9999)
    private String embed;
}
