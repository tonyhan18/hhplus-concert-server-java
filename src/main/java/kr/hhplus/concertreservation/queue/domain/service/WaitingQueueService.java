package kr.hhplus.concertreservation.queue.domain.service;

import org.springframework.stereotype.Service;

import kr.hhplus.concertreservation.common.uuid.UUIDGenerator;
import kr.hhplus.concertreservation.queue.domain.model.dto.WaitingQueueInfo;
import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;
import kr.hhplus.concertreservation.queue.domain.repository.WaitingQueueReader;
import kr.hhplus.concertreservation.queue.domain.repository.WaitingQueueWriter;
import lombok.RequiredArgsConstructor;

/**
 * RequiredArgsConstructor는 final 필드에 대한 생성자를 자동으로 생성합니다.
 * 이 어노테이션을 사용하면, 생성자 주입을 통해 의존성을 주입받을 수 있습니다.
 */
@Service
@RequiredArgsConstructor
public class WaitingQueueService {
    private final WaitingQueueReader waitingQueueReader;
    private final WaitingQueueWriter waitingQueueWriter;

    private final UUIDGenerator uuidGenerator;

    /**
     * * 대기열을 생성합니다.
     * 대기열은 사용자의 ID와 UUID로 구성됩니다.
     * @param userId
     * @return
     */
    public WaitingQueue createWaitingQueue(final Long userId) {
        final String token = uuidGenerator.generate();
        WaitingQueue waitingQueue = new WaitingQueue(userId, token);
        waitingQueueWriter.save(waitingQueue);
        return waitingQueue;
    }

    /**
     * 대기열 정보를 가져옵니다.
     * 대기열 정보는 대기열의 ID, 사용자 ID, 토큰, 상태, 활성화 시간, 만료 시간,
     * 마지막 작업 시간, 생성 시간, 업데이트 시간 및 대기 번호를 포함합니다.
     * @param token
     * @return
     */
    public WaitingQueueInfo getWaitingQueueInfo(final String token) {
        final WaitingQueue currentWaitingQueue = waitingQueueReader.getByToken(token);

        // 대기열이 활성화되어 있는 경우, 대기 번호를 가져옵니다.
        // 대기열이 활성화되어 있지 않은 경우, 대기 번호는 0입니다.
        if(currentWaitingQueue.isWaiting()) {
            
            Long waitingOrder = waitingQueueReader.getLatestActivatedQueue()
            .map(getLatestActivatedQueue -> currentWaitingQueue.getId() - getLatestActivatedQueue.getId())
            .orElse(currentWaitingQueue.getId());

            return WaitingQueueInfo.of(currentWaitingQueue, waitingOrder);
        }
        return WaitingQueueInfo.of(currentWaitingQueue, 0L);
    }

    public void checkActivatedQueue(final String token) {
        final WaitingQueue currentWaitingQueue = waitingQueueReader.getByToken(token);

        if(currentWaitingQueue.isExpired()) {
            throw new IllegalStateException("대기열이 만료되었습니다.");
        }

        if(!currentWaitingQueue.isActivated()) {
            throw new IllegalStateException("대기열이 활성화되어 있지 않습니다.");
        }

    }
}
