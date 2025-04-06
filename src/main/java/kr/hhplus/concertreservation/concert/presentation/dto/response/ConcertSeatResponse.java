package kr.hhplus.concertreservation.concert.presentation.dto.response;

import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertSeatStatus;

import java.math.BigDecimal;

public record ConcertSeatResponse(
        Long seatId,
        String number,
        ConcertSeatStatus status,
        BigDecimal price
) {
}
