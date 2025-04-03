package kr.hhplus.concertreservation.concert.presentation.controller;

import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertSeatStatus;
import kr.hhplus.concertreservation.concert.presentation.dto.response.ConcertSeatResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.ConcertroundResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.GetConcertSeatsResponse;
import kr.hhplus.concertreservation.concert.presentation.dto.response.GetConcertroundsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertController {

    @GetMapping("/{concertId}/rounds")
    public ResponseEntity<GetConcertroundsResponse> getAvailablerounds(
            @PathVariable Long concertId
    ) {
        ConcertroundResponse round = new ConcertroundResponse(1L, LocalDateTime.parse("2021-01-01T00:00:00"));
        return ResponseEntity.ok(new GetConcertroundsResponse(List.of(round)));
    }

    @GetMapping("/{concertId}/rounds/{roundId}/seats")
    public ResponseEntity<GetConcertSeatsResponse> getConcertSeats(
            @PathVariable Long concertId,
            @PathVariable Long roundId
    )
    {
        ConcertSeatResponse unAvailableSeat = new ConcertSeatResponse(2L, "A2", ConcertSeatStatus.RESERVED, BigDecimal.valueOf(1000L));
        ConcertSeatResponse availableSeat = new ConcertSeatResponse(1L, "A1",ConcertSeatStatus.AVAILABLE, BigDecimal.valueOf(1000L));

        return ResponseEntity.ok(new GetConcertSeatsResponse(100, List.of(unAvailableSeat), List.of(availableSeat)));
    }

}
