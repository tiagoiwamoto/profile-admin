package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Slf4j
class CourseMapperTest {

    @InjectMocks
    private CourseMapper courseMapper;
    @Mock
    private CourseCategoryMapper courseCategoryMapper;

    @Test
    void toDto() {
        var domain = this.convertObjectToClass("course-response");
        Mockito.when(this.courseCategoryMapper.toDto(Mockito.any())).thenReturn(this.getCourseCategory());
        Assertions.assertNotNull(this.courseMapper.toDto(domain));
    }

    private CourseDomain convertObjectToClass(String fileName) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            var file = Files.readString(Path.of(String.format("src/test/resources/%s.json", fileName)));
            var object = mapper.readValue(file, CourseDomain.class);
            return object;
        }catch (Exception e){
            log.error("Falha ao converter objeto :" + fileName);
            return null;
        }
    }

    private CourseCategoryDto getCourseCategory(){
        var category = new CourseCategoryDto();
        category.setUuid(UUID.randomUUID());
        category.setId(1000L);
        category.setName("name");
        category.setDescription("description");
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }
}