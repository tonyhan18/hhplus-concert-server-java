package kr.hhplus.concertreservation.concert.domain.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import kr.hhplus.concertreservation.concert.domain.model.dto.ReserveConcertCommand;
import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertReservationStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcertReservation {
    private Long id; // 예약 ID
    private Long userId; // 사용자 ID
    private Long concertSessionId; // 공연 세션 ID
    private List<Long> seatIds; // 좌석 ID
    private ConcertReservationStatus status; // 예약 상태
    private long totalPrice; // 총 결제 금액
    private LocalDateTime reservationAt; // 예약 시간
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간
    
    public ConcertReservation(final ReserveConcertCommand command, final long totalPrice, final LocalDateTime reservationAt){
        this.userId = command.userId();
        this.concertSessionId = command.concertSessionId();
        this.seatIds = command.seatIds();
        this.status = ConcertReservationStatus.TEMPORARY_RESERVED;
        this.totalPrice = totalPrice;
        this.reservationAt = reservationAt;
    }
}
