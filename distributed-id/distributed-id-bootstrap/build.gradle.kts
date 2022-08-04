plugins {
    `java-library`
    id("org.springframework.boot")
}

group = "org.myddd.java.distributed"
version = rootProject.extra["projectVersion"]!!

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(project(":distributed-id:distributed-id-api"))
    implementation(project(":distributed-id:distributed-id-application"))

    implementation("org.springframework.boot:spring-boot-starter-web:${rootProject.extra["spring.boot"]}")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-domain:${rootProject.extra["myddd_version"]}")
    implementation("org.myddd:myddd-ioc-spring:${rootProject.extra["myddd_version"]}")

    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.6")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.6")
    implementation("javax.activation:activation:1.1.1")

    implementation("io.grpc:grpc-netty:1.48.0")

    implementation("com.h2database:h2:${rootProject.extra["h2_version"]}")


    testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
    testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")
    testImplementation("org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:1.1.1.Final")

}
