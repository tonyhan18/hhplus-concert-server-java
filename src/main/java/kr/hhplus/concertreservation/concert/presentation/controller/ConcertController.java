package kr.hhplus.concertreservation.concert.presentation.controller;

import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertSeatStatus;
import kr.hhplus.concertreservation.concert.presentation.dto.request.ReserveConcertRequest;
import kr.hhplus.concertreservation.concert.presentation.dto.response.ConcertSeatResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.ConcertSessionResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.GetConcertSeatsResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.GetConcertSessionsResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.ReserveConcertResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.ReserveConcertResponse.ReserveSeatResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertController {

    @GetMapping("/{concertId}/sessions")
    public ResponseEntity<GetConcertSessionsResponse> getAvailablerounds(
            @PathVariable Long concertId
    ) {
        ConcertSessionResponse round = new ConcertSessionResponse(1L, LocalDateTime.parse("2021-01-01T00:00:00"));
        return ResponseEntity.ok(new GetConcertSessionsResponse(List.of(round)));
    }

    @GetMapping("/{concertId}/sessions/{sessionId}/seats")
    public ResponseEntity<GetConcertSeatsResponse> getConcertSeats(
            @PathVariable Long concertId,
            @PathVariable Long roundId
    )
    {
        ConcertSeatResponse unAvailableSeat = new ConcertSeatResponse(2L, "A2", ConcertSeatStatus.TEMPORARY_RESERVED, BigDecimal.valueOf(1000L));
        ConcertSeatResponse availableSeat = new ConcertSeatResponse(1L, "A1",ConcertSeatStatus.AVAILABLE, BigDecimal.valueOf(1000L));

        return ResponseEntity.ok(new GetConcertSeatsResponse(100, List.of(unAvailableSeat), List.of(availableSeat)));
    }

    @PostMapping("/{concertId}/sessions/{sessionsId}/reservations")
    public ResponseEntity<ReserveConcertResponse> reserveConcert(
            @PathVariable Long concertId,
            @PathVariable Long sessionsId,
            @RequestBody ReserveConcertRequest request
    ) {
        ReserveSeatResponse reservedSeat = new ReserveSeatResponse(1L, 1, 100);
        return ResponseEntity.ok(new ReserveConcertResponse(1L, 100, List.of(reservedSeat)));
    }
}
