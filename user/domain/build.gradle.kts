plugins {
    `java-library`
}

group = "org.myddd.java.user"
version = rootProject.extra["projectVersion"]!!


dependencies {
    implementation("org.myddd:myddd-domain:${rootProject.extra["myddd_version"]}")

    testImplementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")
    testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation(project(":user:infra"))
    testImplementation(project(":distributed-id:distributed-id-api"))
    testImplementation(project(":distributed-id:distributed-id-application"))
}

sonarqube {
    isSkipProject = true
}