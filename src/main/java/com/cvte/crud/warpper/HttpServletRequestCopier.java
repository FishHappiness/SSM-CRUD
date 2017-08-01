package com.cvte.crud.warpper;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class HttpServletRequestCopier extends HttpServletRequestWrapper{
    private String body;
    private StringBuilder stringBuilder;
    private BufferedReader bufferedReader;
    private ServletInputStreamCopier servletInputStreamCopier;
    public HttpServletRequestCopier(HttpServletRequest request) throws IOException {
        super(request);
        stringBuilder = new StringBuilder();
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        body = stringBuilder.toString() ;
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        servletInputStreamCopier = new ServletInputStreamCopier(byteArrayInputStream);
        servletInputStreamCopier.read();
        return servletInputStreamCopier;
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
    public String getBody() {
        return this.body;
    }
}

