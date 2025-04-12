package kr.hhplus.concertreservation.queue.domain.model.dto;

import java.time.LocalDateTime;

import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;
import kr.hhplus.concertreservation.queue.domain.model.vo.QueueStatus;

/**
 * WaitingQueueInfo는 대기열 정보를 나타내는 데이터 전송 객체(DTO)입니다.
 * 이 객체는 대기열의 ID, 사용자 ID, 토큰, 상태, 활성화 시간, 만료 시간, 마지막 작업 시간,
 * 생성 시간, 업데이트 시간 및 대기 번호를 포함합니다.
 * 
 * 이 객체는 대기열 정보를 클라이언트에 전달하기 위해 사용됩니다.
 * 
 * 어떤 class가 record로 사용되어야 할까?
 * - DTO는 record로 사용하고, Entity는 class로 사용한다.
 * - DTO는 불변성을 보장하고, Entity는 mutable하게 사용한다.
 * - DTO는 데이터 전송을 위한 객체이고, Entity는 비즈니스 로직을 포함하는 객체이다.
 * - DTO는 단순한 데이터 구조이고, Entity는 복잡한 데이터 구조이다.
 * - DTO는 데이터 전송을 위한 객체이고, Entity는 데이터베이스와의 상호작용을 위한 객체이다.
 * 
 * DTO는 데이터 전송을 위한 객체로, 주로 API 응답이나 요청에 사용됩니다.
 * DTO는 불변성을 보장하고, 단순한 데이터 구조를 가지고 있습니다.
 */
public record WaitingQueueInfo(
        Long id,
        Long userId,
        String token,
        QueueStatus status,
        LocalDateTime activatedAt,
        LocalDateTime expiredAt,
        LocalDateTime lastActionedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long waitingNumber
){

    /**
     * WaitingQueueInfo 객체를 생성하는 정적 팩토리 메서드입니다.
     * 이 메서드는 WaitingQueue 객체와 대기 번호를 인자로 받아
     * WaitingQueueInfo 객체를 생성합니다.
     * @param waitingQueue
     * @param waitingNumber
     * @return
     * 
     * of 메서드는 정적 팩토리 메서드로, WaitingQueueInfo 객체를 생성하는 역할을 합니다.
     * 정적 팩토리 메서드란 클래스의 인스턴스를 생성하는 메서드로, 일반적으로 new 키워드를 사용하지 않고 객체를 생성하는 방법입니다.
     * 정적 팩토리 메서드는 가독성을 높이고, 객체 생성의 복잡성을 줄이며, 객체 생성 시 추가적인 로직을 수행할 수 있습니다.
     */
    public static WaitingQueueInfo of (final WaitingQueue waitingQueue, final Long waitingNumber) {
        return new WaitingQueueInfo(
                waitingQueue.getId(),
                waitingQueue.getUserId(),
                waitingQueue.getToken(),
                waitingQueue.getStatus(),
                waitingQueue.getActivatedAt(),
                waitingQueue.getExpiredAt(),
                waitingQueue.getLastActionedAt(),
                waitingQueue.getCreatedAt(),
                waitingQueue.getUpdatedAt(),
                waitingNumber
        );
    }
}
