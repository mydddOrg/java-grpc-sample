plugins {
    `java-library`
}

group = "org.myddd.java.user"
version = rootProject.extra["projectVersion"]!!

dependencies {
    implementation(project(":user:domain"))
    implementation("org.myddd:myddd-persistence-jpa:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")
    implementation(project(":distributed-id:distributed-id-api"))

    testImplementation("jakarta.transaction:jakarta.transaction-api:${rootProject.extra["transaction-api"]}")
    testImplementation(project(":distributed-id:distributed-id-application"))
}

sonarqube {
    isSkipProject = true
}