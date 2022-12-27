package br.com.tiagoiwamoto.profileadmin.entrypoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IResourceWithFile<T> extends IResource<T>{

    ResponseEntity<T> create(T record, MultipartFile multipartFile);
    ResponseEntity<T> update(T record, MultipartFile multipartFile);

}
