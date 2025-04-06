package kr.hhplus.concertreservation.concert.presentation.dto.response;

import java.util.List;

public record GetConcertroundsResponse(
        List<ConcertroundResponse> rounds
) {
}
