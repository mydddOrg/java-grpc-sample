rootProject.name = "java-grpc-sample"


include("user:domain")
include("user:api")
include("user:infra")
include("user:application")
include("user:grpc-bootstrap")


include("distributed-id:distributed-id-api")
include("distributed-id:distributed-id-application")
include("distributed-id:distributed-id-bootstrap")

include("rest:rest-bootstrap")
