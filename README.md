# Car Service
## Table of Contents

- [Description](#description)
- [Documentation](#documentation)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start / Setup](#quick-start--setup)
- [API](#api)

## Description
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/asys1920/carservice)](https://github.com/asys1920/carservice/releases/tag/v1.0.0)

This microservice is part of the car-rental project which was built
by the Asys course 19/20 at the TH Bingen.

It manages the cars of the car-rental, keeps track of all cars
the car-rental has to offer and keeps track of the status of
the cars.

The Microservice can be monitored by Prometheus.

Logs can be sent to Elasticsearch/Logstash using Filebeat.

## Documentation
See [Management project](https://github.com/asys1920/management) for a documentation of the whole Car-Rental project.
## Features
This microservice can create, update, delete and read cars from a local H2 Database. Furthermore, it exposes health,
info and shutdown endpoints using Spring Boot Actuator. By exposing a special /actuator/prometheus endpoint it can
be monitored using Prometheus. By using Filebeat the logs the microservice generates are sent to Elasticsearch/Logstash.

## Requirements
A JDK with at least Java Version 11.

### Local
### Docker
## Quick Start / Setup
### Run Local
### Run Docker

## API
To see a full documentation view the swagger documentation while running the microservice. You can
find the Swagger Documentation at `http://<host>:<port>/swagger-ui.html` 

Method | Endpoint | Parameters | Request Body | Description
--- | --- | ---  | --- | ---
GET | /cars | N/A | N/A | Gets all Cars
GET | /cars | {id} | N/A | Gets the car with the specified ID
POST | /cars | N/A | Car in JSON Format | Creates the Car Specified in the Request Body
DELETE | /cars | {id} | N/A | Deletes the car with the specified ID
PATCH | /cars | N/A | Car in JSON Format | Updates the car  specified in the request body
