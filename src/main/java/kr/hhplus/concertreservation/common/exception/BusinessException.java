package kr.hhplus.concertreservation.common.exception;

import lombok.Getter;

/**
 * BusinessException 클래스는 비즈니스 로직에서 발생할 수 있는 예외를 나타내는 클래스입니다.
 * 이 클래스는 HTTP 상태 코드, 오류 코드 및 오류 메시지를 포함합니다.
 * 주로 서비스 계층에서 사용됩니다.
 */
@Getter
public class BusinessException extends RuntimeException 
{
    private final int status; // HTTP 상태 코드
    private final String errorCode; // 오류 코드
    private final String message; // 오류 메시지

    /**
     * BusinessException 생성자
     *
     * @param errorCode 오류 코드
     * @param message   오류 메시지
     */
    public BusinessException(int status, String errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
