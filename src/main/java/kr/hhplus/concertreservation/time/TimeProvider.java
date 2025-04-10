package kr.hhplus.concertreservation.time;

import java.time.LocalDateTime;

/**
 * TimeProvider 인터페이스는 현재 시간을 제공하는 메서드를 정의합니다.
 * 이 인터페이스를 구현하는 클래스는 시스템의 현재 시간을 반환하는 기능을 제공합니다.
 */
public interface TimeProvider {
    LocalDateTime now();
}
