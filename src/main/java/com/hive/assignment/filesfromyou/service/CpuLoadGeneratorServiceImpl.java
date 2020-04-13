package com.hive.assignment.filesfromyou.service;

import co.elastic.apm.api.CaptureSpan;
import com.hive.assignment.filesfromyou.monitor.CaptureCustomTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CpuLoadGeneratorServiceImpl implements CpuLoadGeneratorService {

  private static final Logger LOGGER = LogManager.getLogger(CpuLoadGeneratorServiceImpl.class);

  @Override
  @CaptureCustomTransaction()
  public String generateLoad(int seconds) {
    final int NUM_OF_ITERATIONS = 1000;
    long start = System.nanoTime();
    for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
      spin(seconds);
    }
    String message = "Load was generated for " + (System.nanoTime() - start) / 1000000 +
            "ms (expected " + (NUM_OF_ITERATIONS * seconds) + ")";
    LOGGER.info(message);
    return message;
  }

  @Override
  @CaptureSpan("spinOperation")
  public void spin(int milliseconds) {
    long sleepTime = milliseconds * 1000000L; // convert to nanoseconds
    long startTime = System.nanoTime();
    while ((System.nanoTime() - startTime) < sleepTime) {}
  }
}
