package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_skills")
@RequiredArgsConstructor
@Getter
@Setter
public class SkillDomain extends AbstractDomain{

    private String category;
    @Column(length = 9999)
    private String habilities;
}
