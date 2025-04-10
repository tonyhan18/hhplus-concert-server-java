package kr.hhplus.concertreservation.queue.domain.repository;

import java.util.Optional;

import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;

public interface WaitingQueueReader {
    WaitingQueue getById(Long id);
    WaitingQueue getByToken(String token);
    Optional<WaitingQueue> getLatestActivatedQueue();
}
