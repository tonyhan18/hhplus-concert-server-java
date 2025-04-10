package kr.hhplus.concertreservation.queue.domain.model.vo;

import lombok.Getter;

/**
 * vo 는 DDD에서 Value Object를 의미합니다.
 * Value Object는 불변성을 가지며, 주로 속성 값으로 사용됩니다.
 * 
 * QueueStatus는 대기열의 상태를 나타내는 열거형입니다.
 * 각 상태는 대기, 활성, 만료를 나타냅니다.
 */
@Getter
public enum QueueStatus {
    WAITING("대기"),
    ACTIVATED("활성"),
    EXPIRED("만료");

    private final String value;

    QueueStatus(String value) {
        this.value = value;
    }
}
