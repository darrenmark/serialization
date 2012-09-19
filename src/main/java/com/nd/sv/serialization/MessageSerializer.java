package com.nd.sv.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 */
public interface MessageSerializer {
    void writeTo(Message message, OutputStream outputStream) throws IOException;

    Message readFrom(InputStream inputStream) throws IOException;
}
