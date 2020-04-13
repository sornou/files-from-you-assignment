package com.hive.assignment.filesfromyou.service;

public interface CpuLoadGeneratorService {
  String generateLoad(int seconds);

  void spin(int milliseconds);
}
