package kr.hhplus.concertreservation.queue.application.usecase;

import kr.hhplus.concertreservation.common.UseCase;
import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;
import kr.hhplus.concertreservation.queue.domain.service.WaitingQueueService;
import kr.hhplus.concertreservation.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateWaitingQueueUseCase {
    private final UserService userService;
    private final WaitingQueueService waitingQueueService;

    public WaitingQueue createWaitingQueue(final long userId) {
        userService.checkUserExists(userId);
        return waitingQueueService.createWaitingQueue(userId);
    }
}
