package kr.hhplus.concertreservation.concert.domain.repository;

import java.util.List;

import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertReservation;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertSeat;

public interface ConcertWriter {
    List<ConcertSeat> saveAll(List<ConcertSeat> concertSeats); // 공연 좌석 저장
    ConcertReservation save(ConcertReservation concertReservation); // 공연 예약 저장
}
