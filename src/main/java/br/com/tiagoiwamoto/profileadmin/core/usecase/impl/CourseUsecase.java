package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseMapper;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseUsecase {


    private final CourseAdapter courseAdapter;
    private final CourseCategoryAdapter courseCategoryAdapter;
    private final ImageAndThumbAdapter imageAndThumbAdapter;
    private final CourseMapper courseMapper;
    private final String PATH = "files/courses/";

    public CourseDto createOrUpdate(CourseDto courseDto, MultipartFile multipartFile){
        log.info("iniciando usecase de courseUsecase createOrUpdate");
        Path path;
        ImageDto imageDto;
        if(Objects.isNull(courseDto.getId())){
            log.info("courseUsecase createOrUpdate -> será criado um novo registro");
            UUID scholarityUuid = UUID.randomUUID();
            path = Paths.get(PATH
                    .concat(courseDto.getCourseCategoryUuid().toString())
                    .concat("/")
                    .concat(scholarityUuid.toString())
            );
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            courseDto.setUuid(scholarityUuid);
        }else{
            log.info("courseUsecase createOrUpdate -> será atualizado o registro");
            path = Paths.get(PATH
                    .concat(courseDto.getCourseCategoryUuid().toString())
                    .concat("/")
                    .concat(courseDto.getUuid().toString())
            );
            var courseDomain = this.courseAdapter.recoveryByUuid(courseDto.getUuid());
            courseDomain.setPathOfImageThumb(courseDomain.getPathOfImageThumb());
            courseDomain.setPathOfImage(courseDomain.getPathOfImage());
            courseDto.setCreatedAt(courseDomain.getCreatedAt());
            courseDto.setUpdatedAt(courseDomain.getUpdatedAt());
            imageDto = this.imageAndThumbAdapter.validUpdateOfImage(path, multipartFile, courseDomain);
        }
        courseDto.setPathOfImage(imageDto.getPathOfImage());
        courseDto.setPathOfImageThumb(imageDto.getPathOfThumb());
        log.info("Convertendo courseDto para courseDomain");
        var courseDomain = this.courseMapper.toDomain(courseDto);
        courseDomain.createOrUpdate();
        courseDomain.setCourseCategory(this.courseCategoryAdapter.recoveryByUuid(courseDto.getCourseCategoryUuid()));
        var response = this.courseAdapter.save(courseDomain);
        var responseDto = this.courseMapper.toDto(response);
        log.info("Convertendo courseDomain para courseDto");
        return responseDto;
    }

    public List<CourseDto> recoveryRecords(UUID uuid){
        log.info(String.format("recuperando registros para %s", PATH));
        var category = this.courseCategoryAdapter.recoveryByUuid(uuid);
        var response = this.courseAdapter.all(category);
        var listOfRecordsDtos = response
                .stream()
                .map(certification -> this.courseMapper.toDto(certification))
                .collect(Collectors.toList());

        log.info(String.format("registros recuperados e convertidos para %s, resultado: %s", PATH, listOfRecordsDtos));
        return listOfRecordsDtos;
    }

    public CourseDto recoveryRecord(UUID uuid){
        log.info(String.format("procurando registro: %s para: %s", uuid, PATH));
        var response = this.courseAdapter.recoveryByUuid(uuid);
        log.info(String.format("registro encontrado: %s para: %s", response, PATH));
        return this.courseMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        log.info(String.format("removendo registro: %s para: %s", uuid, PATH));
        var course = this.courseAdapter.recoveryByUuid(uuid);
        Path path = Paths.get(PATH.concat(course.getCourseCategory().getUuid().toString()).concat(File.separator).concat(uuid.toString()));
        this.courseAdapter.delete(uuid);
        log.info(String.format("registro removido com sucesso para: %s", PATH));
        this.imageAndThumbAdapter.removeFiles(path, course.getPathOfImage(), course.getPathOfImageThumb());
    }

}
