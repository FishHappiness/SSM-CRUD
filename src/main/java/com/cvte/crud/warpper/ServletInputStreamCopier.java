package com.cvte.crud.warpper;

import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ServletInputStreamCopier extends ServletInputStream {
    private ByteArrayInputStream byteArrayInputStream;

    public ServletInputStreamCopier( ByteArrayInputStream byteArrayInputStream) {
        this.byteArrayInputStream = byteArrayInputStream;
    }
    @Override
    public int read() throws IOException {
        return byteArrayInputStream.read();
    }
}
