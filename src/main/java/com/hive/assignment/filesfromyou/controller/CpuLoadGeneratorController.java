package com.hive.assignment.filesfromyou.controller;

import com.hive.assignment.filesfromyou.service.CpuLoadGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cpu-load-generator")
public class CpuLoadGeneratorController {
  private final CpuLoadGeneratorService cpuLoadGeneratorService;

  @Autowired
  public CpuLoadGeneratorController(CpuLoadGeneratorService cpuLoadGeneratorService) {
    this.cpuLoadGeneratorService = cpuLoadGeneratorService;
  }

  @PostMapping("/{seconds}")
  public ResponseEntity<String> generateLoad(@PathVariable("seconds") int seconds) {
    String s = cpuLoadGeneratorService.generateLoad(seconds);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(s);
  }
}
