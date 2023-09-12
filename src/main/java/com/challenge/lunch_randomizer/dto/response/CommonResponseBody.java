package com.challenge.lunch_randomizer.dto.response;

import java.util.HashMap;
import java.util.Map;

public final class CommonResponseBody {

    public static final String KEY_RESULT = "result";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_DATA = "data";
    public static final String KEY_RESULT_SUCCESS = "Success";
    public static final String KEY_RESULT_FAIL = "Fail";

    private Map<String, Object> jsonObj;

    public CommonResponseBody() {
        jsonObj = new HashMap<String, Object>();
        jsonObj.put(KEY_RESULT, "");
        jsonObj.put(KEY_ERROR_CODE, "");
        jsonObj.put(KEY_ERROR_MESSAGE, "");
        jsonObj.put(KEY_DATA, new HashMap<>());
    }

    public void setResult(String result) {
        jsonObj.put(KEY_RESULT, result);
    }
    public void setErrorCode(String errorCode) {
        jsonObj.put(KEY_ERROR_CODE, errorCode);
    }
    public void setErrorMessage(String errorMessage) {
        jsonObj.put(KEY_ERROR_MESSAGE, errorMessage);
    }
    public void setData(Map<String, Object> data) {
        jsonObj.put(KEY_DATA, data);
    }

    public String getResult() {
       return jsonObj.get(KEY_RESULT).toString();
    }
    public String getErrorCode() {
        return jsonObj.get(KEY_ERROR_CODE).toString();
    }
    public String getErrorMessage() {
        return jsonObj.get(KEY_ERROR_MESSAGE).toString();
    }
    public Map<String, Object> getData(){
        return jsonObj.get(KEY_DATA) != null ? (Map<String, Object>) jsonObj.get(KEY_DATA) : null;
    }

    public Map<String, Object> getMap(){
        return jsonObj;
    }
}
