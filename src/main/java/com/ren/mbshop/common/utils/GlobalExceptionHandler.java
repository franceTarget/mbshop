package com.ren.mbshop.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.ren.mbshop.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> errorResult() {
		Map<String, Object> errorResultMap = new HashMap<>();
		errorResultMap.put("code", 500);
		errorResultMap.put("msg", "系统异常！");
		return errorResultMap;
	}
}
