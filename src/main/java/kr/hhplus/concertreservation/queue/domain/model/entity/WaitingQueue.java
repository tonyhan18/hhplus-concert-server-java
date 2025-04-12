package kr.hhplus.concertreservation.queue.domain.model.entity;

import java.time.LocalDateTime;

import kr.hhplus.concertreservation.queue.domain.model.vo.QueueStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * WaitingQueue 클래스는 대기열을 나타내는 엔티티입니다.
 * 이 클래스는 대기열의 상태, 생성 및 업데이트 시간을 포함하여 대기열에 대한 정보를 저장합니다.
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WaitingQueue {
    private Long id;
    private Long userId;
    private String token;
    private QueueStatus status;
    private LocalDateTime activatedAt;
    private LocalDateTime expiredAt;
    private LocalDateTime lastActionedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WaitingQueue(final Long userId, final String token) {
        this.token = token;
        this.userId = userId;
        this.status = QueueStatus.WAITING;
        this.lastActionedAt = LocalDateTime.now();
        this.activatedAt = null;
        this.expiredAt = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isWaiting() {
        return this.status == QueueStatus.WAITING;
    }
    public boolean isActivated() {
        return this.status == QueueStatus.ACTIVATED;
    }
    public boolean isExpired() {
        return this.status == QueueStatus.EXPIRED;
    }
}
