package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseMapper;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseUsecase {

    private final CourseAdapter courseAdapter;
    private final CourseCategoryAdapter courseCategoryAdapter;
    private final ImageAndThumbAdapter imageAndThumbAdapter;
    private final CourseMapper courseMapper;
    private final String PATH = "files/courses/";

    public CourseDto createOrUpdate(CourseDto courseDto, MultipartFile multipartFile){
        //TODO: Melhorar logs
        Path path;
        ImageDto imageDto;
        if(Objects.isNull(courseDto.getId())){
            UUID scholarityUuid = UUID.randomUUID();
            path = Paths.get(PATH
                    .concat(courseDto.getCourseCategoryUuid().toString())
                    .concat("/")
                    .concat(scholarityUuid.toString())
            );
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            courseDto.setUuid(scholarityUuid);
        }else{
            path = Paths.get(PATH
                    .concat(courseDto.getCourseCategoryUuid().toString())
                    .concat("/")
                    .concat(courseDto.getUuid().toString())
            );
            CourseDomain courseDomain = this.courseAdapter.recoveryByUuid(courseDto.getUuid());
            courseDomain.setPathOfImageThumb(courseDomain.getPathOfImageThumb());
            courseDomain.setPathOfImage(courseDomain.getPathOfImage());
            if(!Objects.isNull(multipartFile)){
                imageDto = this.imageAndThumbAdapter.replaceImage(
                        multipartFile,
                        path,
                        courseDomain.getPathOfImage(),
                        courseDomain.getPathOfImageThumb()
                );
            }else{
                imageDto = new ImageDto(courseDomain.getPathOfImage(), courseDomain.getPathOfImageThumb());
            }
        }
        courseDto.setPathOfImage(imageDto.getPathOfImage());
        courseDto.setPathOfImageThumb(imageDto.getPathOfThumb());
        var courseDomain = this.courseMapper.toDomain(courseDto);
        courseDomain.setCourseCategory(this.courseCategoryAdapter.recoveryByUuid(courseDto.getCourseCategoryUuid()));
        var response = this.courseAdapter.save(courseDomain);
        return this.courseMapper.toDto(response);
    }

    public List<CourseDto> recoveryRecords(UUID uuid){
        var category = this.courseCategoryAdapter.recoveryByUuid(uuid);
        var response = this.courseAdapter.all(category);
        var listOfRecordsDtos = response
                .stream()
                .map(certification -> this.courseMapper.toDto(certification))
                .collect(Collectors.toList());

        return listOfRecordsDtos;
    }

    public CourseDto recoveryRecord(UUID uuid){
        var response = this.courseAdapter.recoveryByUuid(uuid);
        return this.courseMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        var course = this.courseAdapter.recoveryByUuid(uuid);
        Path path = Paths.get(PATH.concat(course.getCourseCategory().getUuid().toString()).concat(File.separator).concat(uuid.toString()));
        this.courseAdapter.delete(uuid);
        this.imageAndThumbAdapter.removeFiles(path, course.getPathOfImage(), course.getPathOfImageThumb());
    }

}
