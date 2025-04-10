package kr.hhplus.concertreservation.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiControllerAdvice 클래스는 REST API에서 발생하는 예외를 처리하는 클래스입니다.
 * 이 클래스는 @RestControllerAdvice 어노테이션을 사용하여 모든 REST 컨트롤러에서 발생하는 예외를 처리합니다.
 * @param e
 * @return
 */
@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    /**
     * BusinessException을 처리하는 메서드입니다.
     * @param e BusinessException
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse("500", e.getMessage()));
    }
}
