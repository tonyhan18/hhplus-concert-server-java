package kr.hhplus.concertreservation.concert.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConcertroundResponse(
        Long roundId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime concertAt
) {
}
