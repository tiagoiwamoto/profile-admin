package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public abstract class AbstractResource<T> {

    private IUsecaseCreateUpdate<T> usecase;
    private String path;

    public AbstractResource(IUsecaseCreateUpdate<T> usecase, String path) {
        this.usecase = usecase;
        this.path = path;
    }

    @GetMapping
    public ResponseEntity<List<T>> index(){
        return ResponseEntity.ok().body(this.usecase.recoveryRecords());
    }

    @PostMapping
    public ResponseEntity<T> create(
            @RequestBody T record){

        var response = this.usecase.createOrUpdate(record);
        return ResponseEntity.created(URI.create(this.path))
                .body(response);
    }

    @PutMapping
    public ResponseEntity<T> update(
            @RequestBody T record){

        var response = this.usecase.createOrUpdate(record);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<T> recoveryScholarity(@PathVariable(name = "uuid") UUID uuid){
        return ResponseEntity.ok().body(this.usecase.recoveryRecord(uuid));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity removeScholarity(@PathVariable(name = "uuid") UUID uuid){
        this.usecase.removeRecord(uuid);
        return ResponseEntity.noContent().build();
    }
}
