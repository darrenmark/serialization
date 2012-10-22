package com.nd.sv.serialization;

import java.util.Date;
import java.util.List;

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

    public Attribute(String name, Boolean data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.BOOLEAN;
    }


    public Attribute(String name, Data data) {
        this.name = name;
        this.data = data;
        dataType = AttributeType.OBJECT;
    }

    public Attribute(String name, List<Attribute> list) {
        this.name = name;
        this.data = list;
        dataType = AttributeType.LIST;
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

    public boolean getBoolean() {
        if(dataType != AttributeType.BOOLEAN) {
            throw new UnsupportedOperationException("getBoolean is not supported because the data type is not of type boolean");
        }
        return (Boolean) data;
    }

    public Data getObject() {
        if(dataType != AttributeType.OBJECT) {
            throw new UnsupportedOperationException("getData is not supported because the data type is not a Object");
        }
        return (Data) data;
    }

    public List<Attribute> getList() {
        if(dataType != AttributeType.LIST) {
            throw new UnsupportedOperationException("getData is not supported because the data type is not a List");
        }
        return (List<Attribute>) data;
    }

    public List<Attribute> getObjectList(String key) {
        return getObject().getList(key);
    }

    public String getObjectString(String key) {
        return getObject().getString(key);
    }

    public Long getObjectLong(String key) {
        return getObject().getLong(key);
    }

    public Integer getObjectInteger(String key) {
        return getObject().getInteger(key);
    }

    public Date getObjectDate(String key) {
        return getObject().getDate(key);
    }

    public Double getObjectDouble(String key) {
        return getObject().getDouble(key);
    }

    public boolean getObjectBoolean(String key) {
        return getObject().getBoolean(key);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", dataType=" + dataType +
                ", data=" + data +
                '}';
    }
}
