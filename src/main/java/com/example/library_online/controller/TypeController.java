package com.example.library_online.controller;

import com.example.library_online.dto.type.TypeResponse;
import com.example.library_online.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/type")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TypeController {
    private final TypeService typeService;

    @PostMapping("/add")
    public void addType(@RequestParam String name, @RequestHeader("Authorization") String token){
        typeService.addType(name, token);
    }
    @GetMapping("/types")
    public List<TypeResponse> typeResponses(){
        return typeService.getAll();
    }
}
