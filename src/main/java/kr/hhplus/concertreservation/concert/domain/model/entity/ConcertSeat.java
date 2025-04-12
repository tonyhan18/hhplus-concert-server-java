package kr.hhplus.concertreservation.concert.domain.model.entity;

import java.time.LocalDateTime;

import kr.hhplus.concertreservation.concert.domain.exception.ConcertSeatUnavailableException;
import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertSeatStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcertSeat {
    private Long id; // 좌석 ID
    private Long concertSessionId; // 공연 세션 ID
    private int seatNumber; // 좌석 번호
    private ConcertSeatStatus status; // 좌석 상태
    private long price; // 좌석 가격
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간

    public boolean isAvailableForReservation() {
        return this.status == ConcertSeatStatus.AVAILABLE;
    }

    public void reserve(){
        if(!isAvailableForReservation()){
            throw new ConcertSeatUnavailableException("예약 가능한 좌석이 아닙니다.");
        }
        this.status = ConcertSeatStatus.TEMPORARY_RESERVED;
    }
}
