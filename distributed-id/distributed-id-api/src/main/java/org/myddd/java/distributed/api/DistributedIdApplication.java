package org.myddd.java.distributed.api;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;

public interface DistributedIdApplication {
    Int64Value distributedId(Empty request);
}
