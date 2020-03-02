package com.todolist.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = { "com.todolist" })
@EnableJpaRepositories(basePackages = { "com.todolist" })
public class RepositoryConfig {

}