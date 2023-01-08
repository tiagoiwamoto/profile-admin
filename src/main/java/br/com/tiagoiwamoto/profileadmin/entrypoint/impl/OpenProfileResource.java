package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/open/profile")
public class OpenProfileResource {

    @GetMapping
    public ResponseEntity index(){
        return ResponseEntity.ok("resource aberto para chamadas");
    }

}
