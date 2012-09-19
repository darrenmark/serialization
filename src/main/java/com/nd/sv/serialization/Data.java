package com.nd.sv.serialization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 */
public class Data {
    private List<Attribute> attributes = new ArrayList<>();

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public Date getDate(String name)  {
        return findAttribute(name).getDate();
    }

    public Integer getInteger(String name)  {
        return findAttribute(name).getInteger();
    }

    public Long getLong(String name)  {
        return findAttribute(name).getLong();
    }

    public Double getDouble(String name)  {
        return findAttribute(name).getDouble();
    }

    public String getString(String name)  {
        return findAttribute(name).getString();
    }

    public Data getObject(String name) {
        return findAttribute(name).getObject();
    }

    public void putDate(String name, Date date) {
        checkArgument(!attributeNameExists(name), "Attribute already exists with name ->" + name);
        attributes.add(new Attribute(name, date));
    }

    public void putInteger(String name, Integer integer) {
        checkArgument(!attributeNameExists(name), "Attribute already exists with name ->" + name);
        attributes.add(new Attribute(name, integer));
    }

    public void putLong(String name, Long _long) {
        checkArgument(!attributeNameExists(name), "Attribute already exists with name ->" + name);
        attributes.add(new Attribute(name, _long));
    }

    public void putDouble(String name, Double _double) {
        checkArgument(!attributeNameExists(name), "Attribute already exists with name ->" + name);
        attributes.add(new Attribute(name, _double));
    }

    public void putString(String name, String string) {
        checkArgument(!attributeNameExists(name), "Attribute already exists with name ->" + name);
        attributes.add(new Attribute(name, string));
    }

    public void putObject(String name, Data object) {
        checkArgument(!attributeNameExists(name), "Attribute already exists with name ->" + name);
        attributes.add(new Attribute(name, object));
    }

    public void remove(String name) {
        if(!attributeNameExists(name)) {
            return;
        }
        attributes.remove(findAttribute(name));
    }

    private Attribute findAttribute(String name) {
        for(Attribute attribute: attributes) {
            if(attribute.getName().equals(name)) {
                return attribute;
            }
        }
        throw new IllegalArgumentException("No object exists with name ->" + name);
    }

    private boolean attributeNameExists(String name) {
        for(Attribute attribute: attributes) {
            if(attribute.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
