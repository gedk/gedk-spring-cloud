package com.gedk.cloud.order.exception;

import com.gedk.cloud.order.controller.vo.Result;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理器
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/11 16:50
 */
@Slf4j
public class GlobalExceptionResolver extends DefaultHandlerExceptionResolver {
    private static class GsonView implements View {
        private final static String CONTENT_TYPE = "application/json;charset=utf-8";
        private Result<?> data;
        private int status;

        GsonView(int status,Result<?> data){
            this.data = data;
            this.status = status;
        }

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.setContentType(this.getContentType());
            response.setStatus(status);
            response.getWriter().write(new Gson().toJson(data));
        }

        @Override
        public String getContentType() {
            return CONTENT_TYPE;
        }
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return new ModelAndView(new GsonView(getHttpStatus(ex),new Result<>("",getErrorMsg(ex))));
    }

    private int getHttpStatus(Exception ex){
        int sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            sc = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            sc = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            sc = HttpServletResponse.SC_NOT_ACCEPTABLE;
        } else if (ex instanceof MissingPathVariableException) {
            sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        } else if (ex instanceof MissingServletRequestParameterException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof ServletRequestBindingException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof ConversionNotSupportedException) {
            sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        } else if (ex instanceof TypeMismatchException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotReadableException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof HttpMessageNotWritableException) {
            sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        } else if (ex instanceof MethodArgumentNotValidException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof MissingServletRequestPartException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof BindException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof NoHandlerFoundException) {
            sc = HttpServletResponse.SC_NOT_FOUND;
        } else if (ex instanceof AsyncRequestTimeoutException) {
            sc = HttpServletResponse.SC_SERVICE_UNAVAILABLE;
        }
        return sc;
    }

    private String getErrorMsg(Exception ex){
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
            final StringBuilder errorMsgBuilder = new StringBuilder("");
            allErrors.forEach(objectError -> {
                if(StringUtils.isEmpty(errorMsgBuilder.toString())){
                    errorMsgBuilder.append(objectError.getDefaultMessage());
                } else {
                    errorMsgBuilder.append(",");
                    errorMsgBuilder.append(objectError.getDefaultMessage());
                }
            });
            return errorMsgBuilder.toString();
        }
        return ex.getMessage();
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}