package kr.hhplus.concertreservation.queue.presentation.dto.response;

import kr.hhplus.concertreservation.queue.domain.model.dto.WaitingQueueInfo;
import kr.hhplus.concertreservation.queue.domain.model.vo.QueueStatus;

public record GetWaitingQueueStatusResponse(
        Long waitingQueueId,
        Long userId,
        QueueStatus status,
        Long waitingNumber
) {
    public static GetWaitingQueueStatusResponse of (final WaitingQueueInfo waitingQueueInfo) {
        return new GetWaitingQueueStatusResponse(
                waitingQueueInfo.id(),
                waitingQueueInfo.userId(),
                waitingQueueInfo.status(),
                waitingQueueInfo.waitingNumber()
        );
    }
}
