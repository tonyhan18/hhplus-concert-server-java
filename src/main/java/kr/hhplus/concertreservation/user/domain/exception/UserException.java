package kr.hhplus.concertreservation.user.domain.exception;

import kr.hhplus.concertreservation.common.exception.BusinessException;

public class UserException extends BusinessException{
    public UserException(UserErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.getErrorCode(), errorCode.getMessage());
    }

    public UserException(UserErrorCode errorCode, String message) {
        super(errorCode.getStatus(), errorCode.getErrorCode(), message);
    }

    public UserException(UserErrorCode errorCode, Throwable cause) {
        super(errorCode.getStatus(), errorCode.getErrorCode(), cause.getMessage());
        initCause(cause);
    }
}