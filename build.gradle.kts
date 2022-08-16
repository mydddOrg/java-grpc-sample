buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.17")
    }
}

plugins {
    java
    `java-library`
    id("org.springframework.boot") version "2.7.2"
    id("jacoco")
    id("org.sonarqube") version "3.3"
}

val projectVersion = "1.0.0-SNAPSHOT"

extra["projectVersion"] = projectVersion
extra["slf4jVersion"] = "1.7.36"
extra["spring.boot"] = "2.7.2"
extra["junit.version"] = "5.8.2"
extra["myddd_version"] = "0.3.4-ALPHA"
extra["h2_version"] = "2.1.214"
extra["protobuf-java"] = "3.19.1"
extra["annotation-api"] = "1.3.2"
extra["transaction-api"] = "1.3.3"
extra["mysql_jdbc"] = "8.0.26"
extra["mariadb-java-client"] = "3.0.3"
extra["guava.version"] = "31.1-jre"
extra["mockito.version"] = "4.3.1"
extra["log4j-version"] = "2.17.2"

extra["grpc-version"] = "1.48.1"
extra["protobuf"] = "3.21.4"

group = "org.myddd.java.micro"
version = extra["projectVersion"]!!


allprojects {
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }

    repositories {

        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/spring/")
        }

        mavenCentral()

        maven {
            setUrl("https://maven.myddd.org/releases/")
        }
        maven {
            setUrl("https://maven.myddd.org/snapshots/")
        }

    }
}


subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "org.sonarqube")

    jacoco {
        toolVersion = "0.8.7"
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }

    tasks.test {
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test) // tests are required to run before generating the report
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        jvmArgs = listOf("--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED","--add-opens=java.base/java.nio=ALL-UNNAMED","-Dio.netty.tryReflectionSetAccessible=true")
    }

    allprojects {
        tasks.withType<JavaCompile> {
            sourceCompatibility = "11"
            targetCompatibility = "11"
        }
    }

    //默认测试依赖
    dependencies {
        implementation("com.google.guava:guava:${rootProject.extra["guava.version"]}")

        testImplementation("org.mockito:mockito-core:${rootProject.extra["mockito.version"]}")
        testImplementation("org.junit.jupiter:junit-jupiter-api:${rootProject.extra["junit.version"]}")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:${rootProject.extra["junit.version"]}")
        testImplementation("org.springframework.boot:spring-boot-starter-test:${rootProject.extra["spring.boot"]}")
        testImplementation("com.h2database:h2:${rootProject.extra["h2_version"]}")

        testImplementation("org.apache.logging.log4j:log4j-core:${rootProject.extra["log4j-version"]}")
        testImplementation("org.apache.logging.log4j:log4j-jpl:${rootProject.extra["log4j-version"]}")
    }
}


tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}



