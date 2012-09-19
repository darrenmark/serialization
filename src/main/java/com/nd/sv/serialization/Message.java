package com.nd.sv.serialization;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 */
public class Message {
    private Data header;
    private Data data;

    public Message(Data header, Data data) {
        this.header = checkNotNull(header);
        this.data = checkNotNull(data);
    }

    public Data getHeader() {
        return header;
    }

    public Data getData() {
        return data;
    }

}
