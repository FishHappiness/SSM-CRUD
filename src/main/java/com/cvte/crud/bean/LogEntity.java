package com.cvte.crud.bean;

public class LogEntity {
    private String tranceId;
    private long elapsed;
    private String requestMethod;
    private String url;
    private String param;
    private String methodName;
    private String result;

    public LogEntity() {
    }

    public LogEntity(String tranceId, long elapsed, String requestMethod, String url, String param, String methodName, String result) {
        this.tranceId = tranceId;
        this.elapsed = elapsed;
        this.requestMethod = requestMethod;
        this.url = url;
        this.param = param;
        this.methodName = methodName;
        this.result = result;
    }

    public String getTranceId() {
        return tranceId;
    }

    public void setTranceId(String tranceId) {
        this.tranceId = tranceId;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "tranceId='" + tranceId + '\'' +
                ", elapsed=" + elapsed +
                ", requestMethod='" + requestMethod + '\'' +
                ", url='" + url + '\'' +
                ", param='" + param + '\'' +
                ", methodName='" + methodName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
