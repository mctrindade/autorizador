package br.com.vr.autorizador.exception;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CartaoExistenteException.class })
	public ResponseEntity<Object> handleCartaoExistenteException(CartaoExistenteException ex, HttpServletRequest request) {
		return defaultResponseEntity(request, ex.getBody(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(value = { CartaoInexistenteSaldoException.class })
	public ResponseEntity<Object> handleCartaoInexistenteSaldoException(CartaoInexistenteSaldoException ex, HttpServletRequest request) {
		return defaultResponseEntity(request, ex.getBody(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { CartaoSaldoException.class, CartaoSenhaInvalidaException.class, CartaoInexistenteException.class })
	public ResponseEntity<Object> handleCartaoException(CartaoException ex, HttpServletRequest request) {
		return defaultResponseEntity(request,ex.getBody(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	private ResponseEntity<Object> defaultResponseEntity(HttpServletRequest request, Optional<Object> bodyOp, HttpStatus status) {
		ModelAndView mdv = defaultModelAndView(request, bodyOp, status);
		return ResponseEntity.status(status).body(mdv.getModel());
	}

	private ModelAndView defaultModelAndView(HttpServletRequest request, Optional<Object> bodyOp, HttpStatus status) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("status", status.value());
		bodyOp.ifPresent(s -> {mav.addObject("body", bodyOp.get());});
		return mav;
	}

}
