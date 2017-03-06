package com.github.cruiser.exception;

import com.alibaba.fastjson.JSON;
import com.github.cruiser.dto.BaseResult;
import com.github.cruiser.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 错误信息统一处理
 * 对未处理的错误信息做一个统一处理
 * @author yingjun
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 全局处理Exception
	 * 错误的情况下返回500
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherExceptions(final Exception ex,
														final WebRequest req) {
		ResultEnum resultEnum = ResultEnum.getMsgByCode(ex.getMessage());
		BaseResult<String> result = new BaseResult<String>(resultEnum.getResultCode(),
				resultEnum.getResultMsg());
		return new ResponseEntity<Object>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
