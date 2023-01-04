package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CourseUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/courses")
@RequiredArgsConstructor
public class CourseResource {

    private final CourseUsecase courseUsecase;

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<List<CourseDto>> index(@PathVariable(name = "uuid") UUID uuid){
        return ResponseEntity.ok().body(this.courseUsecase.recoveryRecords(uuid));
    }

    @PostMapping
    public ResponseEntity<CourseDto> create(
            CourseDto courseDto,
            @RequestParam(name = "file") MultipartFile multipartFile){

        var response = this.courseUsecase.createOrUpdate(courseDto, multipartFile);
        return ResponseEntity.created(URI.create("/courses/".concat(response.getUuid().toString())))
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CourseDto> update(
            CourseDto courseDto,
            @RequestParam(name = "file", required = false) MultipartFile multipartFile){

        var response = this.courseUsecase.createOrUpdate(courseDto, multipartFile);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/course/{uuid}")
    public ResponseEntity<CourseDto> recoveryRecord(@PathVariable(name = "uuid") UUID uuid){
        return ResponseEntity.ok().body(this.courseUsecase.recoveryRecord(uuid));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity removeRecord(@PathVariable(name = "uuid") UUID uuid){
        this.courseUsecase.removeRecord(uuid);
        return ResponseEntity.noContent().build();
    }

}
