package com.zhaopin.core.dto.report;

import java.util.List;

/**
 * Created by zhou.hao on 2017/7/4.
 */
public class LineCode {
    private String key;
    private List<Code> codeList;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Code> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<Code> codeList) {
        this.codeList = codeList;
    }
}
