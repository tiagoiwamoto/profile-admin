package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRecoveryException;
import br.com.tiagoiwamoto.profileadmin.core.repository.CourseRepository;
import br.com.tiagoiwamoto.profileadmin.core.repository.out.CourseMetric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseAdapter extends AbstractAdapter<CourseDomain> {

    private CourseRepository repository;
    private String domain;
    public CourseAdapter(CourseRepository repository) {
        super(repository, CourseDomain.class.getSimpleName());
        this.repository = repository;
        this.domain = CourseDomain.class.getSimpleName();
    }

    public List<CourseDomain> all(CourseCategoryDomain courseCategoryDomain){
        log.info(String.format("iniciando busca: metodo: all(), para o dominio: %s", domain));
        try{
            var response = this.repository.findAllByCourseCategory(courseCategoryDomain);
            log.info(String.format("Resposta da consulta para o domínio %s: %s", domain, response));
            return response;
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao realizar consulta no dominio %s",
                            domain),
                    e);
            throw new RecordRecoveryException();
        }
    }

    public List<CourseDomain> latestCourses() {
        log.info(String.format("iniciando busca: metodo: latestCourses(), para o dominio: %s", domain));
        try {
            var response = this.repository.findTopLatestCourses();
            log.info(String.format("Resposta da consulta para o domínio %s: %s", domain, response));
            return response;
        } catch (Exception e) {
            log.error(
                    String.format(
                            "Falha ao realizar consulta no dominio %s",
                            domain),
                    e);
            throw new RecordRecoveryException();
        }
    }


    public List<CourseMetric> metricTotal(){
    log.info(String.format("iniciando busca: metodo: metricTotal(), para o dominio: %s", domain));
    try{
        var response = this.repository.findCourseByDuration();
        log.info(String.format("Resposta da consulta para o domínio %s: %s", domain, response));
        return response;
    }catch (Exception e){
        log.error(
                String.format(
                        "Falha ao realizar consulta no dominio %s",
                        domain),
                e);
        throw new RecordRecoveryException();
    }
    }

}
