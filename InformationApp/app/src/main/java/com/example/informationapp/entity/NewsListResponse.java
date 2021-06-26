package com.example.informationapp.entity;

import java.io.Serializable;
import java.util.List;

public class NewsListResponse implements Serializable {
    private String reason;
    private ResultDTO result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public int getErrorCode() {
        return error_code;
    }

    public void setErrorCode(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultDTO implements Serializable {
        private String stat;
        private String page;
        private String pageSize;
        private List<NewsEntity> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public List<NewsEntity> getData() {
            return data;
        }

        public void setData(List<NewsEntity> data) {
            this.data = data;
        }
    }
}
