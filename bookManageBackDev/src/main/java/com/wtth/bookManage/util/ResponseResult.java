package com.wtth.bookManage.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseResult<T> {
    /**
     * 1.status状态值：代表本次请求response的状态结果。
     */
    private Integer status;
    /**
     * 2.response描述：对本次状态码的描述。
     */
    private String message;
    /**
     * 3.data数据：本次返回的数据。
     */
    private T data;
    /**
     * 4.请求路径
     */
    private String requestPath;

    /**
     * 成功，创建ResResult：有data数据
     */
    public static ResponseResult succeed(Object data) {
        ResponseResult result = new ResponseResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMessage("success");
        result.setRequestPath(ContextUtil.getRequest().getRequestURI());
        result.setData(data);
        return result;
    }
}
