package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseMapper;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class CourseUsecaseTest {

    @InjectMocks
    private CourseUsecase courseUsecase;
    @Mock
    private CourseAdapter courseAdapter;
    @Mock
    private CourseCategoryAdapter courseCategoryAdapter;
    @Mock
    private ImageAndThumbAdapter imageAndThumbAdapter;
    @Mock
    private CourseMapper courseMapper;

    @Test
    @DisplayName("Testar a funcionalidade de cadastro de novo registro")
    public void testCreate(){
        var request = this.convertObjectToClass("course-request");
        request.setCourseCategoryUuid(UUID.randomUUID());
        var responseDto = this.convertObjectToClass("course-response");
        var response = this.toDomain(request);
        var multipart = new MockMultipartFile("meu-arquivo.png", "".getBytes());
        Mockito.when(this.imageAndThumbAdapter.storeImage(Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDto("/image", "/thumb"));
        Mockito.when(this.courseAdapter.save(Mockito.any())).thenReturn(response);
        Mockito.when(this.courseMapper.toDomain(Mockito.any())).thenReturn(response);
        Mockito.when(this.courseMapper.toDto(Mockito.any())).thenReturn(responseDto);
        Assertions.assertNotNull(this.courseUsecase.createOrUpdate(request, multipart));

    }
    @Test
    @DisplayName("Testar a funcionalidade de atualização de registro")
    public void testUpdate(){
        var request = this.convertObjectToClass("course-update-request");
        request.setCourseCategoryUuid(UUID.randomUUID());
        var responseDto = this.convertObjectToClass("course-response");
        var response = this.toDomain(request);
        response.setCourseCategory(this.getCourseCategory());
        var multipart = new MockMultipartFile("meu-arquivo.png", "".getBytes());
        Mockito.when(this.imageAndThumbAdapter.validUpdateOfImage(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new ImageDto("/image", "/thumb"));
        Mockito.when(this.courseAdapter.save(Mockito.any())).thenReturn(response);
        Mockito.when(this.courseAdapter.recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
        Mockito.when(this.courseMapper.toDomain(Mockito.any())).thenReturn(response);
        Mockito.when(this.courseMapper.toDto(Mockito.any())).thenReturn(responseDto);
        Assertions.assertNotNull(this.courseUsecase.createOrUpdate(request, multipart));

    }

    @Test
    @DisplayName("Testar funcionalidade de recuperar todos os registros de um domínio")
    public void testRecoveryRecords(){
        var responseDto = this.convertObjectToClass("course-response");
        var response = this.toDomain(responseDto);
        Mockito.when(this.courseCategoryAdapter.recoveryByUuid(Mockito.any(UUID.class))).thenReturn(this.getCourseCategory());
        Mockito.when(this.courseAdapter.all(Mockito.any())).thenReturn(List.of(response));
        Mockito.when(this.courseMapper.toDto(Mockito.any())).thenReturn(responseDto);
        Assertions.assertNotNull(this.courseUsecase.recoveryRecords(UUID.randomUUID()));
    }

    @Test
    @DisplayName("Testar funcionalidade de recuperar um registro por UUID")
    public void testRecoveryRecord(){
        var responseDto = this.convertObjectToClass("course-response");
        var response = this.toDomain(responseDto);
        Mockito.when(this.courseAdapter.recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
        Mockito.when(this.courseMapper.toDto(Mockito.any())).thenReturn(responseDto);
        Assertions.assertNotNull(this.courseUsecase.recoveryRecord(UUID.randomUUID()));
    }

    @Test
    @DisplayName("Testar funcionalidade de remover um registro por UUID")
    public void testRemoveRecord(){
        var responseDto = this.convertObjectToClass("course-response");
        var response = this.toDomain(responseDto);
        response.setCourseCategory(this.getCourseCategory());
        Mockito.when(this.courseCategoryAdapter.recoveryByUuid(Mockito.any(UUID.class))).thenReturn(this.getCourseCategory());
        Mockito.when(this.courseAdapter.recoveryByUuid(Mockito.any(UUID.class))).thenReturn(response);
        Mockito.doNothing().when(this.courseAdapter).delete(Mockito.any(UUID.class));
        Mockito.doNothing().when(this.imageAndThumbAdapter).removeFiles(Mockito.any());
        this.courseUsecase.removeRecord(UUID.randomUUID());
    }

    private CourseDto convertObjectToClass(String fileName) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var dtoObject = mapper.readValue(file, CourseDto.class);
            return dtoObject;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

    private CourseDomain toDomain(CourseDto dto){
        try {
            var course = new CourseDomain();
            BeanUtils.copyProperties(dto, course);
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CourseCategoryDomain getCourseCategory(){
        var category = new CourseCategoryDomain();
        category.setUuid(UUID.randomUUID());
        category.setId(1000L);
        category.setName("name");
        category.setDescription("description");
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }

}