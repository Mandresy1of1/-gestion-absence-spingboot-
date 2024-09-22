package com.hei.absence.gestion.controller;

import com.hei.absence.gestion.model.COR;
import com.hei.absence.gestion.service.CORService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cors")
public class CORController {

    private final CORService corService;

    public CORController(CORService corService) {
        this.corService = corService;
    }

    @GetMapping
    public ResponseEntity<List<COR>> getAllCORs() {
        List<COR> cors = corService.getAllCORs();
        return new ResponseEntity<>(cors, HttpStatus.OK);
    }
}
