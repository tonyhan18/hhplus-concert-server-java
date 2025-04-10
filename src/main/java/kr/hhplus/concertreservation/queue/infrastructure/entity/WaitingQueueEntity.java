package kr.hhplus.concertreservation.queue.infrastructure.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import kr.hhplus.concertreservation.common.auditing.BaseEntity;
import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;
import kr.hhplus.concertreservation.queue.domain.model.vo.QueueStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity 클래스는 대기열에 대한 정보를 저장하는 JPA 엔티티입니다.
 * NoArgsConstructor 어노테이션을 사용하여 기본 생성자를 생성합니다.
 * Table 어노테이션을 사용하여 데이터베이스 테이블과 매핑됩니다.
 * Getter 어노테이션을 사용하여 필드에 대한 getter 메서드를 자동으로 생성합니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "waiting_queue")
/**
 * 대기열 엔티티 클래스입니다.
 * 이 클래스는 대기열에 대한 정보를 저장하는 엔티티입니다.
 * JPA를 사용하여 데이터베이스와 매핑됩니다.
 * 
 * BaseEntity 클래스를 상속받아 createdAt과 updatedAt 필드를 포함합니다.
 * BaseEntity 클래스는 Spring Data JPA의 감사(Auditing) 기능을 활용하여 엔티티의 생성 및 수정 시간을 자동으로 관리합니다.
 * BaseEntity를 쓴 이유는 대기열 엔티티가 생성 및 수정 시간을 자동으로 관리하기 위함입니다.
 * BaseEntity는 여러 엔티티에서 공통적으로 사용되는 필드를 정의하고, 이를 상속받아 재사용할 수 있도록 설계되었습니다.
 */
public class WaitingQueueEntity extends BaseEntity {
    /**
     * @Id 어노테이션은 이 필드가 엔티티의 기본 키임을 나타냅니다.
     * @GeneratedValue 어노테이션은 이 필드의 값을 자동으로 생성하도록 설정합니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String token;

    /**
     * @Enumerated 어노테이션은 이 필드가 열거형(enum) 타입임을 나타냅니다.
     */
    @Enumerated(EnumType.STRING)
    private QueueStatus status;

    @Column(name = "activated_at")
    private LocalDateTime activatedAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "last_actioned_at")
    private LocalDateTime lastActionedAt;

    public WaitingQueueEntity(final WaitingQueue waitingQueue) {
        this.id = waitingQueue.getId();
        this.userId = waitingQueue.getUserId();
        this.token = waitingQueue.getToken();
        this.status = waitingQueue.getStatus();
        this.activatedAt = waitingQueue.getActivatedAt();
        this.expiredAt = waitingQueue.getExpiredAt();
        this.lastActionedAt = waitingQueue.getLastActionedAt();
    }

    /**
     * WaitingQueueEntity 객체를 WaitingQueue 객체로 변환하는 메서드입니다.
     * 이 메서드는 WaitingQueueEntity 객체의 필드를 사용하여
     * WaitingQueue 객체를 생성합니다.
     * 
     * Builder 메소드를 사용했기 때문에 객체 생성 시 각 필드에 어떤 값이 설정되는지 명확히 알 수 있습니다.
     * @return
     */
    public WaitingQueue toDomain() {
        return WaitingQueue.builder()
                .id(id)
                .userId(userId)
                .token(token)
                .status(status)
                .activatedAt(activatedAt)
                .expiredAt(expiredAt)
                .lastActionedAt(lastActionedAt)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
