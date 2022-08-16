import com.google.protobuf.gradle.*

plugins {
    `java-library`
    idea
    id("com.google.protobuf")
}

group = "org.myddd.java.user"
version = rootProject.extra["projectVersion"]!!


dependencies {
    api("org.myddd:myddd-grpc:${rootProject.extra["myddd_version"]}")
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