plugins {
    `java-library`
}

group = "org.myddd.java.user"
version = rootProject.extra["projectVersion"]!!

dependencies {

    implementation(project(":user:api"))
    implementation(project(":user:domain"))


    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-query-channel:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-persistence-jpa:${rootProject.extra["myddd_version"]}")

    implementation("jakarta.transaction:jakarta.transaction-api:${rootProject.extra["transaction-api"]}")

    testImplementation(project(":user:infra"))
    testImplementation(project(":distributed-id:distributed-id-api"))
    testImplementation(project(":distributed-id:distributed-id-application"))
    testImplementation("io.grpc:grpc-netty:${rootProject.extra["grpc-version"]}")


}
sonarqube {
    isSkipProject = true
}