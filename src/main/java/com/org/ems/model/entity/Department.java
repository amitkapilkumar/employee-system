package com.org.ems.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// https://www.baeldung.com/jpa-hibernate-associations
// https://www.baeldung.com/hibernate-identifiers
// https://www.baeldung.com/jpa-composite-primary-keys
// https://www.baeldung.com/spring-annotations-resource-inject-autowire
// https://www.edureka.co/blog/interview-questions/cybersecurity-interview-questions/
/*
 Top Ten OWASP Vulnerabilities
 Injection
 Broken Auth
 Sensitive Data Exposure
 XML External Entities
 Broken Access control
 Security MisConfiguration
 Cross Site scripting
 Insecure Deserialization
 Using component with known vulnerabilities
 Insufficient logging and Monitoring
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
