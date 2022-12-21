package br.com.tiagoiwamoto.profileadmin.entrypoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloKeycloak {

    @GetMapping
    public ResponseEntity hello(){
        return ResponseEntity.ok().build();
    }

}
