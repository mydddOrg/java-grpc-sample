import com.google.protobuf.gradle.*

plugins {
    `java-library`
    idea
    id("com.google.protobuf")
}

group = "org.myddd.java.distribute"
version = rootProject.extra["projectVersion"]!!


dependencies {
    api("io.grpc:grpc-kotlin-stub:1.3.0")
    api("io.grpc:grpc-protobuf:1.48.0")
    api("com.google.protobuf:protobuf-kotlin:3.21.3")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("javax.transaction:transaction-api:1.1")
}

sourceSets.main {
    proto.srcDir("src/main/protobuf")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.3"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.48.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}