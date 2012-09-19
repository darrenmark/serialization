package com.nd.sv.serialization.protobuf;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import com.nd.sv.serialization.protobuf.ProtobufMessage.Object;

/**
 */
public class ProtobufMessageTest {

    @Test
    public void testSerializedDeserialize() throws IOException {
        ProtobufMessage.Object inputData = ProtobufMessage.Object.newBuilder().addAttributes(ProtobufMessage.Attribute.newBuilder().setName("test").setDouble(10.0).setDataType(ProtobufMessage.Attribute.DataType.DOUBLE)).build();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        inputData.writeTo(outputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        Object outputData  = Object.parseFrom(byteArrayInputStream);
        assertThat("Data is same", outputData.toString(), is(equalTo(inputData.toString())));
    }
}
