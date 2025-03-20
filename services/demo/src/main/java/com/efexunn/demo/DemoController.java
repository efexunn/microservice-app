package com.efexunn.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/deneme")
    public ResponseEntity<String> deneme(){
        return ResponseEntity.ok("EFELERIN EFESI");
    }
}
