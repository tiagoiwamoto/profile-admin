package br.com.tiagoiwamoto.profileadmin.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tbl_courses_categories")
@RequiredArgsConstructor
@Getter
@Setter
public class CourseCategoryDomain extends AbstractDomain{

    private String name;
    @Column(length = 9999)
    private String description;
    @OneToMany
    @JoinColumn(name = "course_category_id")
    private List<CourseDomain> courses;
}
