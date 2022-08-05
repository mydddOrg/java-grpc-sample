import com.google.protobuf.gradle.*

plugins {
    `java-library`
    idea
    id("com.google.protobuf")
}

group = "org.myddd.java.user"
version = rootProject.extra["projectVersion"]!!


dependencies {
    api("io.grpc:grpc-protobuf:${rootProject.extra["grpc-version"]}")
    api("io.grpc:grpc-stub:${rootProject.extra["grpc-version"]}")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("javax.annotation:javax.annotation-api:${rootProject.extra["annotation-api"]}")
}

sourceSets.main {
    proto.srcDir("src/main/protobuf")
    java.srcDir("build/generated/proto/main/myddd-grpc")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${rootProject.extra["protobuf"]}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${rootProject.extra["grpc-version"]}"
        }
        id("myddd-grpc") {
            artifact = "org.myddd.plugin:myddd-grpc-gradle-plugin:0.1.0"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("myddd-grpc")
            }
        }
    }
}