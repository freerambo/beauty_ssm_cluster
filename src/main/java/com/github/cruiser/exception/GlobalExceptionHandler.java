package com.github.cruiser.exception;

import com.github.cruiser.dto.JsonErrorResponse;
import com.github.cruiser.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 错误信息统一处理
 * 对未处理的错误信息做一个统一处理
 *
 * @author yingjun
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 全局处理Exception
     * 错误的情况下返回500
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<JsonErrorResponse<String>> handleOtherExceptions(final Exception ex,
                                                        final WebRequest req) {
        ResultEnum resultEnum = ResultEnum.getMsgByCode(ex.getMessage());
        JsonErrorResponse<String> result = new JsonErrorResponse<String>(
                resultEnum.getResultCode(),
                resultEnum.getResultMsg());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
