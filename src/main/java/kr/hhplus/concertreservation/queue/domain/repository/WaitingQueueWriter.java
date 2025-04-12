package kr.hhplus.concertreservation.queue.domain.repository;

import kr.hhplus.concertreservation.queue.domain.model.entity.WaitingQueue;

public interface WaitingQueueWriter {
    WaitingQueue save(WaitingQueue waitingQueue);
}
