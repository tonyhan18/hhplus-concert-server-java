package kr.hhplus.concertreservation.queue.application.usecase;

import kr.hhplus.concertreservation.common.UseCase;
import kr.hhplus.concertreservation.queue.domain.model.dto.WaitingQueueInfo;
import kr.hhplus.concertreservation.queue.domain.service.WaitingQueueService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetWaitingQueueUseCase {
    private final WaitingQueueService waitingQueueService;

    public WaitingQueueInfo getWaitingQueueInfo(final String token)
    {
        return waitingQueueService.getWaitingQueueInfo(token);
    }
}
