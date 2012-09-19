package com.nd.sv.serialization;

import com.nd.sv.serialization.protobuf.ProtobufMessageSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 */
public class MessageSerializationSpecs {

    private MessageSerializer messageSerializer = new ProtobufMessageSerializer();
    private XStream xstream = new XStream(new DomDriver());

    @Test
    public void itShouldNotLooseDateInSerialization() throws Exception {
        Data data = createDummyData();
        data.putObject("innerObject", createDummyData());
        Message originalMessage = new Message(createDummyData(), data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        messageSerializer.writeTo(originalMessage, outputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        Message outputMessage = messageSerializer.readFrom(byteArrayInputStream);
        assertThat("Message data is same", xstream.toXML(outputMessage), is(equalTo(xstream.toXML(originalMessage))));
    }


    private Data createDummyData() throws Exception{
        Data object = new Data();
        object.putString("name", "Darren");
        object.putDate("dob", new SimpleDateFormat("MM/dd/yyyy").parse("11/23/2012"));
        object.putInteger("age", 22);
        object.putDouble("height", 22.2);
        object.putLong("income", 22000L);
        return object;
    }
}
