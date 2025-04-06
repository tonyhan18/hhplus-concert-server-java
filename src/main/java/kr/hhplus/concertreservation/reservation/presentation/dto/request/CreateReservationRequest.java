package kr.hhplus.concertreservation.reservation.presentation.dto.request;

import java.util.List;

public record CreateReservationRequest(
        Long userId,
        Long concertId,
        Long roundId,
        List<Long> seatIds) {
}
