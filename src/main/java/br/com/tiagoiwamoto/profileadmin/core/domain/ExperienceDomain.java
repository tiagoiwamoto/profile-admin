package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_experiences")
@RequiredArgsConstructor
@Getter
@Setter
public class ExperienceDomain extends AbstractDomain{

    private String job;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(length = 9999)
    private String description;

}
