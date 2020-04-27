package routing.zull.models;

import java.io.Serializable;

public class BaseData<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 结果集
     */
    private T data;
    public BaseData(){}
    public static <T> BaseData<T> result(Integer code, String msg, T data) {
        BaseData<T> apiResult = new BaseData<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        apiResult.setData(data);
        return apiResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
