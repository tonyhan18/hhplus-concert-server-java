package kr.hhplus.concertreservation.user.domain.exception;

/**
 * 유저 도메인에서 발생하는 예외 코드
 * 
 * enum에서는 @Getter를 안 쓰는 이유는 enum 자체의 특성과 기본 제공 기능 때문
 */
public enum UserErrorCode {

    USER_NOT_FOUND(404, "USER_001", "해당 유저를 찾을 수 없습니다"),
    USER_POINT_NOT_FOUND(404, "USER_002", "포인트를 찾을 수 없습니다"),
    USER_POINT_NOT_ENOUGH(400, "USER_003", "포인트가 부족합니다"),
    POINT_AMOUNT_INVALID(400, "USER_004", "충전/사용할 포인트 금액이 유효하지 않습니다");

    private final int status;
    private final String errorCode;
    private final String message;

    UserErrorCode(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
