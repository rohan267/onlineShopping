package org.eshop.eshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleNoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Page is not Found");
		mv.addObject("errorDescription","Page not available");
		mv.addObject("title","404 Page not found");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView productNotFound() {
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Product not Found");
		mv.addObject("errorDescription","Product you are looking for not available");
		mv.addObject("title","Product unavailable");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception _x) {
		ModelAndView mv = new ModelAndView("error");
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		_x.printStackTrace(pw);
		
		mv.addObject("errorTitle","Error occured");
		mv.addObject("errorDescription",sw.toString());
		mv.addObject("title","Contact you administrator");
		return mv;
	}
}
