package br.com.tiagoiwamoto.profileadmin.entrypoint;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IResourceCreateUpdate<T> extends IResource<T>{

    ResponseEntity<T> create(T record);
    ResponseEntity<T> update(T record);

}
