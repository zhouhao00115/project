package com.zhaopin.core.pojo.ramanPojo;

import java.util.List;

public class BasePojo {
    private String message;
    private int code;
    private List<RamanData> result;
    private String version;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RamanData> getResult() {
        return result;
    }

    public void setResult(List<RamanData> result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
