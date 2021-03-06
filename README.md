# Bechraoui Amine Microservices with springboot

This repository contains source code examples used to support the on-line courses about the Spring Framework.

You can learn more about the courses here:
* [Spring Boot Microservices with Spring Cloud](https://www.udemy.com/spring-boot-microservices-with-spring-cloud-beginner-to-guru/?couponCode=GIT_HUB2)
* [Spring Framework 5: Beginner to Guru](https://www.udemy.com/course/spring-framework-5-beginner-to-guru/?couponCode=GITHUB_SFGPETCLINIC)
* [Testing Spring Boot: Beginner to Guru](https://www.udemy.com/testing-spring-boot-beginner-to-guru/?couponCode=GITHUB_REPO_SF5B2G)

docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -e MYSQL_USER=springboot -e MYSQL_PASSWORD=springboot -d mysql:5.6

docker run -d -p 9411:9411 openzipkin/zipkin

docker run -it --rm -p 8161:8161 -p 61616:61616 -d vromero/activemq-artemis
artemis / simetraehcapa
activemq-artemis-docker
https://github.com/vromero/activemq-artemis-docker

compiler params : 

-parameters -Amapstruct.defaultComponentModel=spring

-Dspring.profiles.active=local,local-discovery

zipkin project :

https://github.com/openzipkin-attic/docker-zipkin