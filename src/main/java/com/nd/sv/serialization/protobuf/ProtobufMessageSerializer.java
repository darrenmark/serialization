package com.nd.sv.serialization.protobuf;

import com.nd.sv.serialization.*;
import com.nd.sv.serialization.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
public class ProtobufMessageSerializer implements MessageSerializer {

    @Override
    public void writeTo(Message message, OutputStream outputStream) throws IOException {
        ProtobufMessage.DataPacket.newBuilder().setHeader(toProtobufObject(message.getHeader())).setData(toProtobufObject(message.getData())).build().writeTo(outputStream);
    }

    @Override
    public Message readFrom(InputStream inputStream) throws IOException {
        ProtobufMessage.DataPacket dataPacket = ProtobufMessage.DataPacket.parseFrom(inputStream);
        return new Message(toData(dataPacket.getHeader()), toData(dataPacket.getData()));
    }

    private ProtobufMessage.Object toProtobufObject(Data object) {
        ProtobufMessage.Object.Builder objectBuilder = ProtobufMessage.Object.newBuilder();
        for(Attribute attribute: object.getAttributes())  {
            objectBuilder.addAttributes(toAttribute(attribute));
        }
        return objectBuilder.build();
    }

    private com.nd.sv.serialization.protobuf.ProtobufMessage.List toProtobufList(List<Attribute> attributes) {
        ProtobufMessage.List.Builder builder =  ProtobufMessage.List.newBuilder();
        for(Attribute attribute: attributes) {
            builder.addAttributes(toAttribute(attribute));
        }
        return builder.build();
    }

    private ProtobufMessage.Attribute toAttribute(Attribute attribute) {
        ProtobufMessage.Attribute.Builder  attributeBuilder = ProtobufMessage.Attribute.newBuilder();
        attributeBuilder.setName(attribute.getName());
        attributeBuilder.setDataType(com.nd.sv.serialization.protobuf.ProtobufMessage.Attribute.DataType.valueOf(attribute.getDataType().name()));
        if(attribute.getDataType() == AttributeType.STRING) {
            attributeBuilder.setString(attribute.getString());
        }
        if(attribute.getDataType() == AttributeType.BOOLEAN) {
            attributeBuilder.setBoolean(attribute.getBoolean());
        }
        if(attribute.getDataType() == AttributeType.INTEGER) {
            attributeBuilder.setInteger(attribute.getInteger());
        }
        if(attribute.getDataType() == AttributeType.LONG) {
            attributeBuilder.setLong(attribute.getLong());
        }
        if(attribute.getDataType() == AttributeType.DOUBLE) {
            attributeBuilder.setDouble(attribute.getDouble());
        }
        if(attribute.getDataType() == AttributeType.DATE) {
            attributeBuilder.setLong(attribute.getDate().getTime());
        }
        if(attribute.getDataType() == AttributeType.OBJECT) {
            attributeBuilder.setObject(toProtobufObject(attribute.getObject()));
        }
        if(attribute.getDataType() == AttributeType.LIST) {
            attributeBuilder.setList(toProtobufList(attribute.getList()));
        }
        return attributeBuilder.build();
    }

    private Data toData(ProtobufMessage.Object object) {
        Data result = new Data();
        for(ProtobufMessage.Attribute attribute: object.getAttributesList()) {
            result.getAttributes().add(toAttribute(attribute));
        }
        return result;
    }

    private List<Attribute> toList(ProtobufMessage.List list) {
        List<Attribute> result = new ArrayList<>();
        for(ProtobufMessage.Attribute attribute: list.getAttributesList()) {
            result.add(toAttribute(attribute));
        }
        return result;
    }

    private Attribute toAttribute(ProtobufMessage.Attribute attribute) {
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.DATE) {
            return new Attribute(attribute.getName(), new Date(attribute.getLong()));
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.STRING) {
            return new Attribute(attribute.getName(), attribute.getString());
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.BOOLEAN) {
            return new Attribute(attribute.getName(), attribute.getBoolean());
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.INTEGER) {
            return new Attribute(attribute.getName(), attribute.getInteger());
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.LONG) {
            return new Attribute(attribute.getName(), attribute.getLong());
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.DOUBLE) {
            return new Attribute(attribute.getName(), attribute.getDouble());
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.OBJECT) {
            return new Attribute(attribute.getName(), toData(attribute.getObject()));
        }
        if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.LIST) {
            return new Attribute(attribute.getName(), toList(attribute.getList()));
        }
        throw new UnsupportedOperationException("Attribute Type " + attribute.getDataType().name() + " is not supported");
    }
}
