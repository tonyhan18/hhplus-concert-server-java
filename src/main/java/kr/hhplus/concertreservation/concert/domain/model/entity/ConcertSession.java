package kr.hhplus.concertreservation.concert.domain.model.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcertSession {
    private Long id; // 공연 세션 ID
    private Long concertId; // 공연 ID
    private LocalDateTime conertAt; // 공연 일시
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간
    private int totalSeatCount; // 총 좌석 수
}
