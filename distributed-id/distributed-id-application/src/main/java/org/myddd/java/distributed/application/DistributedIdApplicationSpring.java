package org.myddd.java.distributed.application;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import org.myddd.java.distributed.api.DistributedIdApplication;

import javax.inject.Named;
import java.net.Inet4Address;
import java.net.UnknownHostException;

@Named
public class DistributedIdApplicationSpring implements DistributedIdApplication {

    private SnowflakeDistributeId snowflakeDistributeId = new SnowflakeDistributeId(0,0);

    @Override
    public Int64Value distributedId(Empty request) {
        return Int64Value.of(snowflakeDistributeId.nextId());
    }


    @Override
    public StringValue hostIp(Empty request) {
        try {
            return StringValue.of(Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}
