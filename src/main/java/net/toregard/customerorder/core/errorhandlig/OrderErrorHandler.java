package net.toregard.customerorder.core.errorhandlig;

import net.toregard.customerorder.command.exceptions.OrderCreateException;
import net.toregard.customerorder.command.exceptions.OrderUpdateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class OrderErrorHandler {

    @ExceptionHandler(value = {OrderUpdateException.class, OrderCreateException.class})
    public ResponseEntity<Object> handleException(OrderException orderException, WebRequest request) {
       return new ResponseEntity<>(orderException.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
