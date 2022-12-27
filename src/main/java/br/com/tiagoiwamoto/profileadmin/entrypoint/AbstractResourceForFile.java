package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

public abstract class AbstractResourceForFile<T> implements IResourceWithFile<T> {

    private IUsecaseWithFile<T> usecase;
    private String path;

    public AbstractResourceForFile(IUsecaseWithFile<T> usecase, String path) {
        this.usecase = usecase;
        this.path = path;
    }

    @GetMapping
    public ResponseEntity<List<T>> index(){
        return ResponseEntity.ok().body(this.usecase.recoveryRecords());
    }

    @PostMapping
    public ResponseEntity<T> create(
            @Valid T record,
            @RequestParam(name = "file") MultipartFile multipartFile){

        var response = this.usecase.createOrUpdate(record, multipartFile);
        return ResponseEntity.created(URI.create(this.path))
                .body(response);
    }

    @PutMapping
    public ResponseEntity<T> update(
            @Valid T record,
            @RequestParam(name = "file", required = false) MultipartFile multipartFile){

        var response = this.usecase.createOrUpdate(record, multipartFile);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<T> recoveryRecord(@PathVariable(name = "uuid") UUID uuid){
        return ResponseEntity.ok().body(this.usecase.recoveryRecord(uuid));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity removeRecord(@PathVariable(name = "uuid") UUID uuid){
        this.usecase.removeRecord(uuid);
        return ResponseEntity.noContent().build();
    }
}
