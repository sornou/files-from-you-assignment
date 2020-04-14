# Application Performance Monitoring - PoC
This is a proof of concept as a solution to FilesFromYou assignment

## Mission Statement
The purpose of this project is to demonstrate how to monitor performance over a simple java application

## Core Features
- Spring boot application with a REST end-point for increasing the system cpu load
- Deployment of the application as a docker container
- Deployment of Elastic APM, Elasticsearch and Kibana as docker containers

## Development

To build:

```bash
mvn clean install
```

To deploy services:
```bash
mvn package
```
```bash
docker-compose -f docker/docker-compose.yml up
```

If apm server does not start use:

```bash
docker-compose -f docker/docker-compose.yml restart java-app apm
```

It is because Elasticsearch sometimes take a bit longer to start.

## Architecture
All services will be running as docker containers. Since we are going to monitor performance for the java application, we will run it in a docker container, then the container will be profiled by a java agent provided by Elastic. The java agent collects metrics and sends them to the APM server which is also running in a separate container. Once the metrics are stored in Elasticsearch, the performance can be explored by using Kibana built-in APM dashboards.

### Spring Boot application
The reason for choosing the spring framework is its simplicity to initialize and set up. The application has a REST endpoint which takes an integer as a path variable, then will run 1000 loops for the amount of the path variable in seconds. I also created a very simple java annotation called @CaptureCustomTransaction as a demonstration on how APM API can be used to send customized data and metrics to APM server.

```bash
curl -X POST http://localhost:8080/api/v1/cpu-load-generator/5
```
Will increase the system CPU load for about 5 seconds

### Elastic APM
Elastic APM is an application performance monitoring system built on the Elastic Stack. It allows us to monitor software services and applications in real-time — collect detailed performance information on response time for incoming requests, database queries, calls to caches, external HTTP requests, and more. This makes it easy to pinpoint and fix performance problems quickly. Elastic APM also automatically collects unhandled errors and exceptions. Errors are grouped based primarily on the stacktrace, so you can identify new errors as they appear and keep an eye on how many times specific errors happen. Metrics are another important source of information when debugging production systems. Elastic APM agents automatically pick up basic host-level metrics and agent-specific metrics, like JVM metrics. It also has a very powerful API that can be used for sending customized data such as thread dumps, client system information, user information, etc to the APM Server.



