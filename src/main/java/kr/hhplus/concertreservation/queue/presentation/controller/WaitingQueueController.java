package kr.hhplus.concertreservation.queue.presentation.controller;

import kr.hhplus.concertreservation.queue.application.usecase.CreateWaitingQueueUseCase;
import kr.hhplus.concertreservation.queue.application.usecase.GetWaitingQueueUseCase;
import kr.hhplus.concertreservation.queue.domain.model.vo.QueueStatus;
import kr.hhplus.concertreservation.queue.presentation.dto.request.CreateWaitingQueueRequest;
import kr.hhplus.concertreservation.queue.presentation.dto.response.CreateWaitingQueueResponse;
import kr.hhplus.concertreservation.queue.presentation.dto.response.GetWaitingQueueStatusResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/waiting-queues")
@RequiredArgsConstructor
public class WaitingQueueController {

    private final CreateWaitingQueueUseCase createWaitingQueueUseCase;
    private final GetWaitingQueueUseCase getWaitingQueueUseCase;

    @PostMapping
    public ResponseEntity<CreateWaitingQueueResponse> createWaitingQueue(
            @RequestBody CreateWaitingQueueRequest request
    ) {
        final var waitingQueue = createWaitingQueueUseCase.createWaitingQueue(request.userId());
        return ResponseEntity.status(201).body(
                CreateWaitingQueueResponse.from(waitingQueue)
        );
    }

    @GetMapping
    public ResponseEntity<GetWaitingQueueStatusResponse> getWaitingQueueStatus(
        @RequestHeader("QUEUE-TOKEN") String token,
        @RequestHeader("USER-ID") Long userId
    ) {
        final var waitingQueueInfo = getWaitingQueueUseCase.getWaitingQueueInfo(token);
        return ResponseEntity.ok(
                GetWaitingQueueStatusResponse.of(waitingQueueInfo)
        );
    }
}
