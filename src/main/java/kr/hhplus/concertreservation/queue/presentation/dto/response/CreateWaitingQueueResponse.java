package kr.hhplus.concertreservation.queue.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;
import kr.hhplus.concertreservation.queue.domain.model.vo.QueueStatus;

import java.time.LocalDateTime;

public record CreateWaitingQueueResponse(
        Long id,
        String token,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime joinedAt
) {
    public static CreateWaitingQueueResponse from(WaitingQueue waitingQueue) {
        return new CreateWaitingQueueResponse(
                waitingQueue.getId(),
                waitingQueue.getToken(),
                waitingQueue.getActivatedAt()
        );
    }
}