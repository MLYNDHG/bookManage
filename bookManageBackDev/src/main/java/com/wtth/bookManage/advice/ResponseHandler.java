package com.wtth.bookManage.advice;

import com.wtth.bookManage.util.ContextUtil;
import com.wtth.bookManage.util.ErrorResult;
import com.wtth.bookManage.util.JsonUtil;
import com.wtth.bookManage.util.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = {"com.wtth.bookManage.controller"})
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if ("/get".equals(ContextUtil.getRequest().getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果是异常结果，直接返回。
        if (body instanceof ErrorResult) {
            return body;
        }
        ResponseResult result = ResponseResult.succeed(body);

        // 字符串与对象转换器不同需要额外判断
        if (body instanceof String) {
            return JsonUtil.object2Json(result);
        }

        return result;
    }
}
