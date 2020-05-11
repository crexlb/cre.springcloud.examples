package routing.getway.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.awt.SystemColor.info;

public class BaseData<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 描述
     */
    private String message;
    /**
     * 结果集
     */
    private T data;
    /**
     * 时间戳
     */
    private String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public BaseData() {
    }

    public static <T> BaseData<T> result(Integer code, String msg, T data) {
        BaseData<T> apiResult = new BaseData<>();
        apiResult.setStatus(code);
        apiResult.setMessage(msg);
        apiResult.setData(data);
        return apiResult;
    }

    public Map<String, Object> toMap() throws Exception {
        Map<String, Object> reMap = new HashMap<String, Object>();
        Field[] fields = getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field subField = getClass().getDeclaredField(fields[i].getName());
            subField.setAccessible(true);
            Object o = subField.get(this);
            reMap.put(fields[i].getName(), o);
        }
        return reMap;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
