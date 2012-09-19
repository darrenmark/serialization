package com.nd.sv.serialization;

import java.util.Date;

/**
 */
public class Attribute {
    private String name;
    private AttributeType dataType;
    private java.lang.Object data;

    public Attribute(String name, Date date) {
        this.name = name;
        this.data = date;
        dataType = AttributeType.DATE;
    }

    public Attribute(String name, Long data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.LONG;
    }

    public Attribute(String name, Integer data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.INTEGER;
    }


    public Attribute(String name, Double data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.DOUBLE;
    }

    public Attribute(String name, String data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.STRING;
    }


    public Attribute(String name, Data data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.OBJECT;
    }

    public String getName() {
        return name;
    }

    public AttributeType getDataType() {
        return dataType;
    }

    public Date getDate() {
        if(dataType != AttributeType.DATE) {
            throw new UnsupportedOperationException("getDate is not supported because the data type is not a Date");
        }
        return (Date) data;
    }

    public Integer getInteger() {
        if(dataType != AttributeType.INTEGER) {
            throw new UnsupportedOperationException("getInteger is not supported because the data type is not an Integer");
        }
        return (Integer) data;
    }

    public Long getLong() {
        if(dataType != AttributeType.LONG) {
            throw new UnsupportedOperationException("getLong is not supported because the data type is not a Long");
        }
        return (Long) data;
    }

    public Double getDouble() {
        if(dataType != AttributeType.DOUBLE) {
            throw new UnsupportedOperationException("getDouble is not supported because the data type is not a Double");
        }
        return (Double) data;
    }

    public String getString() {
        if(dataType != AttributeType.STRING) {
            throw new UnsupportedOperationException("getString is not supported because the data type is not a String");
        }
        return (String) data;
    }

    public Data getObject() {
        if(dataType != AttributeType.OBJECT) {
            throw new UnsupportedOperationException("getData is not supported because the data type is not a Object");
        }
        return (Data) data;
    }

}
