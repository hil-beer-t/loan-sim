# Loan Simulator
<img src="https://img.shields.io/badge/in%20development-v0.0.3-green">
<img src="https://img.shields.io/badge/backend-done-red">
<img src="https://img.shields.io/badge/OpenAPI-v3.0.1-green">

Loan simulation app. Client CRUD, create/delete simulation and JWT auth.

## Description

A client should **_sign up_** or **_sign in_**. Take an **_overview_** of your loans if not have any, should have the possibility
to **_create_** a new requisition.

## Domain model 
![Class Diagram v0 0 2](https://user-images.githubusercontent.com/52302576/153309695-6faa9750-5bf8-43a2-bd05-2ee9b39c5ab6.svg)

## API documentation
[Here](https://hil-beer-t-git.gitbook.io/loan-sim-api/)

## Getting Started

### Prerequisites

Backend
* Java 17
* PostgreSQL and PgAdmin (only in [dev] profile)
* Maven

### Back End
__Inside '/backend' directory__

__Install dependencies__
```shell
mvn dependency:resolve
```

__Run spring__
```shell
mvn spring-boot:run
```

## Authors

[Hilbert Digenio](https://github.com/hil-beer-t)

## License

This project is licensed under the MIT License.
