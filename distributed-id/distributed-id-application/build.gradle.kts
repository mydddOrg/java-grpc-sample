plugins {
    `java-library`
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!


dependencies {

    implementation(project(":distributed-id:distributed-id-api"))


    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-query-channel:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-persistence-jpa:${rootProject.extra["myddd_version"]}")

    implementation("jakarta.transaction:jakarta.transaction-api:${rootProject.extra["transaction-api"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation("io.grpc:grpc-netty:${rootProject.extra["grpc-version"]}")
}
