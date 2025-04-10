package kr.hhplus.concertreservation.concert.presentation.dto.response;

import java.util.List;

public record GetConcertSessionsResponse(
        List<ConcertSessionResponse> rounds
) {
}
