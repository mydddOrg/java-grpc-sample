import com.google.protobuf.gradle.*

plugins {
    `java-library`
    idea
    id("com.google.protobuf")
}

group = "org.myddd.java.distribute"
version = rootProject.extra["projectVersion"]!!


dependencies {
    api("io.grpc:grpc-protobuf:${rootProject.extra["grpc-version"]}")
    api("io.grpc:grpc-stub:${rootProject.extra["grpc-version"]}")

    implementation("org.myddd:myddd-utils:${rootProject.extra["myddd_version"]}")
    implementation("javax.annotation:javax.annotation-api:${rootProject.extra["annotation-api"]}")

}

sourceSets.main {
    proto.srcDir("src/main/protobuf")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${rootProject.extra["protobuf"]}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${rootProject.extra["grpc-version"]}"
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