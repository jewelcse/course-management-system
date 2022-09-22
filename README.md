
# Course Management System

A micro-service based rest services that's manages the courses,student and teachers of an institution. There are three micro-services
ex. 1. course micro-service 2. student micro-service 3. teacher micro-service. All three has their own
responsibility.



## Features

- Teacher and Student can Register and Login to the system
- Teacher can create multiple courses, view courses details, update and remove courses, can update his/her account details
- Student can enroll courses, view course details, view teachers profile, can also update his/her own account details


## Tech Stack

**Client:** Spring Boot, Lombok, Mapstruct, Spring Security, Spring Data JPA, Validation API, Swagger2, Swagger UI, Commons Logging

**Database:** postgreSQL

**Testing:** Unit Testing, Integration Testing

**Server:** Tomcate 



## Installation

Install my-project by git clone

```bash
git clone https://github.com/jewelcse/course-management-system.git
```



```bash
cd course-management-system
```
Buid jar files

```bash
mvn package
```
or for skip the testing
```bash
mvn package -Dmaven.test.skip 
```

To build and run the docker container, must have Installed the docker desktop on your local machine.

Before build and run the container, create a customr network first

```bash
docker create network assignment-1-network
```

Build the containers

```bash
docker-compose build --no-cache 
```

Run the containers

```bash
docker compose up
```
## API Reference
[See the full api documention from here](https://documenter.getpostman.com/view/7197408/2s7ZE1P6Xi)



## Authors

- [@Jewel Chowdhury](https://www.github.com/jewelcse)

