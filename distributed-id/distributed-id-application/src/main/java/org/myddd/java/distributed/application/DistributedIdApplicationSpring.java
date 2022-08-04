package org.myddd.java.distributed.application;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import org.myddd.java.distributed.api.DistributedIdApplication;

import javax.inject.Named;

@Named
public class DistributedIdApplicationSpring implements DistributedIdApplication {

    private SnowflakeDistributeId snowflakeDistributeId = new SnowflakeDistributeId(0,0);

    @Override
    public Int64Value distributedId(Empty request) {
        return Int64Value.of(snowflakeDistributeId.nextId());
    }
}
