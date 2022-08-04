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

    testImplementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")
    testImplementation(project(":distributed-id:distributed-id-application"))
}

sonarqube {
    isSkipProject = true
}