package kr.hhplus.concertreservation.concert.domain.repository;

import kr.hhplus.concertreservation.concert.domain.model.entity.Concert;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertSeat;

public interface ConcertReader {
    Concert getConcertById(final Long concertId); // 공연 ID로 공연 조회
    ConcertSeat getConcertSeatById(final Long conertSeatId); // 공연 좌석 ID로 공연 좌석 조회
}
