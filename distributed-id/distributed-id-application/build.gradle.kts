plugins {
    `java-library`
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!


dependencies {

    implementation(project(":distributed-id:distributed-id-api"))

    implementation("io.grpc:grpc-netty:1.48.0")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-query-channel:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-persistence-jpa:${rootProject.extra["myddd_version"]}")

    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
}
