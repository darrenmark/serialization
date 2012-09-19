package com.nd.sv.serialization.protobuf;

import com.nd.sv.serialization.*;
import com.nd.sv.serialization.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

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
            ProtobufMessage.Attribute.Builder  attributeBuilder = ProtobufMessage.Attribute.newBuilder();
            attributeBuilder.setName(attribute.getName());
            attributeBuilder.setDataType(com.nd.sv.serialization.protobuf.ProtobufMessage.Attribute.DataType.valueOf(attribute.getDataType().name()));
            if(attribute.getDataType() == AttributeType.STRING) {
                attributeBuilder.setString(attribute.getString());
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
            objectBuilder.addAttributes(attributeBuilder.build());
        }
        return objectBuilder.build();
    }

    private Data toData(ProtobufMessage.Object object) {
        Data result = new Data();
        for(ProtobufMessage.Attribute attribute: object.getAttributesList()) {
            if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.DATE) {
                result.putDate(attribute.getName(), new Date(attribute.getLong()));
            }
            if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.STRING) {
                result.putString(attribute.getName(), attribute.getString());
            }
            if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.INTEGER) {
                result.putInteger(attribute.getName(), attribute.getInteger());
            }
            if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.LONG) {
                result.putLong(attribute.getName(), attribute.getLong());
            }
            if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.DOUBLE) {
                result.putDouble(attribute.getName(), attribute.getDouble());
            }
            if(AttributeType.valueOf(attribute.getDataType().name()) == AttributeType.OBJECT) {
                result.putObject(attribute.getName(), toData(attribute.getObject()));
            }
        }
        return result;
    }
}
