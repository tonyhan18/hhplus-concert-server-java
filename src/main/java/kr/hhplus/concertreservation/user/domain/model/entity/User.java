package kr.hhplus.concertreservation.user.domain.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Getter는 lombok에서 제공하는 어노테이션으로, 클래스의 모든 필드에 대한 getter 메서드를 자동으로 생성합니다.
 * Builder는 lombok에서 제공하는 어노테이션으로, 빌더 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
 * * 빌더 패턴이란 객체 생성 시 필요한 매개변수를 명시적으로 지정할 수 있는 패턴으로, 가독성을 높이고 코드의 유연성을 증가시킵니다.
 * AllArgsConstructor는 lombok에서 제공하는 어노테이션으로, 모든 필드를 매개변수로 받는 생성자를 자동으로 생성합니다.
 * * access = lombok.AccessLevel.PRIVATE는 생성자의 접근 제어자를 private으로 설정하여 외부에서 직접 호출할 수 없도록 합니다.
 * * 이는 Builder 패턴을 사용하여 객체를 생성하도록 강제하기 위함입니다.
 */
@Getter
@Builder
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class User {
    private Long id; // 사용자 ID
    private String name; // 사용자 이름
    private String email; // 사용자 이메일
    // LocalDateTime이란 Java 8에서 추가된 날짜와 시간 API로, 날짜와 시간을 함께 표현할 수 있는 클래스입니다.
    // LocalDateTime은 날짜와 시간 정보를 모두 포함하고 있으며, 타임존 정보는 포함하지 않습니다.
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
