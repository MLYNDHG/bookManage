package com.Httb.bookManage.advice;

import com.Httb.bookManage.util.ContextUtil;
import com.Httb.bookManage.util.ErrorResult;
import com.Httb.bookManage.util.JsonUtil;
import com.Httb.bookManage.util.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = {"com.Httb.bookManage.controller"})
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Value("${response.exclude.uri}")
    private String[] uris;

    @Override
    // 开关，是否使用ResponseResult包装类
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (uris != null) {
            for (String uri : uris) {
                if ((contextPath + uri).equals(ContextUtil.getRequest().getRequestURI())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

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
