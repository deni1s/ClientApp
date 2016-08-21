package com.startandroid.client.Responses;

/**
 * Created by Денис on 05.08.2016.
 */
public class TokenResponse {

    /**
     * code : 1
     * error : wrong data input
     */

    private MetaBean meta;
    /**
     * accessToken : j23f90vn23b9v43
     */

    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        private String code;
        private String error;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    public static class DataBean {
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
