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

    implementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")

    testImplementation(project(":user:infra"))
    testImplementation(project(":distributed-id:distributed-id-api"))
    testImplementation(project(":distributed-id:distributed-id-application"))
    testImplementation("io.grpc:grpc-netty:1.48.0")

}
sonarqube {
    isSkipProject = true
}