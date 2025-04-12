package kr.hhplus.concertreservation.concert.domain.exception;

import kr.hhplus.concertreservation.common.exception.BusinessException;

public class ConcertException extends BusinessException {
    public ConcertException(ConcertErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
    }

    public ConcertException(ConcertErrorCode errorCode, String message) {
        super(errorCode.getStatus(), errorCode.getCode(), message);
    }

    public ConcertException(ConcertErrorCode errorCode, Throwable cause) {
        super(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        initCause(cause);
    }
}
