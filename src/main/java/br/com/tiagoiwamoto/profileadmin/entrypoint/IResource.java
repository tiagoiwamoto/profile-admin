package br.com.tiagoiwamoto.profileadmin.entrypoint;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IResource<T> {

    ResponseEntity<List<T>> index();
    ResponseEntity<T> recoveryRecord(UUID uuid);
    ResponseEntity<T> removeRecord(UUID uuid);

}
