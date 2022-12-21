package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_profiles")
@RequiredArgsConstructor
@Getter
@Setter
public class ProfileDomain extends AbstractDomain{
    private String name;
    private String title;
    private String subTitle;
    private String email;
    private String phone;
    private Boolean isActive;
    @Column(length = 9999)
    private String description;

}
