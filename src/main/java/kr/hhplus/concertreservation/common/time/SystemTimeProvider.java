package kr.hhplus.concertreservation.common.time;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * SystemTimeProvider 클래스는 TimeProvider 인터페이스를 구현하여 시스템의 현재 시간을 제공하는 클래스입니다.
 * 이 클래스는 LocalDateTime.now() 메서드를 사용하여 현재 시간을 반환합니다.
 */
@Component
public class SystemTimeProvider implements TimeProvider {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
