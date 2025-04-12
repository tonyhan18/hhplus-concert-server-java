package kr.hhplus.concertreservation.common.exception;

/**
 * Java의 record는 불변 데이터 객체를 간단하게 정의하기 위해 도입된 기능입니다. 
 * record를 사용하면 데이터 중심의 클래스를 작성할 때 필요한 필드, 생성자, getter, equals, hashCode, toString 메서드를 자동으로 생성해줍니다.
 * 
 * record로 정의된 클래스는 모든 필드가 final로 선언되며, 객체가 생성된 이후에는 필드 값을 변경할 수 없습니다.
 * 
 * 생성자, getter, equals, hashCode, toString 메서드가 자동으로 생성되므로,
 * 코드의 양을 줄이고 가독성을 높일 수 있습니다.
*/
public record ErrorResponse (String code, String message) {
    /**
     * ErrorResponse 클래스는 오류 응답을 나타내는 레코드 클래스입니다.
     * 이 클래스는 오류 코드와 메시지를 포함합니다.
     *
     * @param code    오류 코드
     * @param message 오류 메시지
     */
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }
}
