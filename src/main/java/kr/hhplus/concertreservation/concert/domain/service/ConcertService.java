package kr.hhplus.concertreservation.concert.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.concertreservation.common.time.TimeProvider;
import kr.hhplus.concertreservation.concert.domain.exception.NotConcertReservationPeriodException;
import kr.hhplus.concertreservation.concert.domain.model.dto.ReserveConcertCommand;
import kr.hhplus.concertreservation.concert.domain.model.entity.Concert;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertReservation;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertSeat;
import kr.hhplus.concertreservation.concert.domain.repository.ConcertReader;
import kr.hhplus.concertreservation.concert.domain.repository.ConcertWriter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertReader concertReader; // 공연 리더 인터페이스
    private final ConcertWriter concertWriter; // 공연 라이터 인터페이스
    private final TimeProvider timeProvider; // 시간 제공자 인터페이스

    /**
     * 공연 예약 메소드
     * * 이 메소드는 공연 예약을 처리하며, 예약 기간이 아닐 경우 예외를 발생시킵니다.
     * @Transactional 어노테이션을 사용하여 트랜잭션을 관리합니다.
     * @param command
     * @return
     */
    @Transactional
    public ConcertReservation reserveConcert(final ReserveConcertCommand command)
    {
        final Concert conert = concertReader.getConcertById(command.concertId()); // 공연 조회
        if(!conert.isWithinReservationPeriod(timeProvider.now())) // 예약 기간 확인
        {
            throw new NotConcertReservationPeriodException("예약 가능한 기간이 아닙니다."); // 예약 기간이 아닐 경우 예외 발생
        }

        final List<ConcertSeat> concertSeats = command.seatIds().stream()
            .map(seatId -> concertReader.getConcertSeatById(seatId)) // 공연 좌석 조회
            .toList(); // 리스트로 변환

        concertSeats.forEach(ConcertSeat::reserve); // 좌석 예약 처리
        concertWriter.saveAll(concertSeats); // 좌석 저장

        long totalPrice = concertSeats.stream()
            .mapToLong(ConcertSeat::getPrice) // 좌석 가격 합산
            .sum(); // 총 가격 계산
        final ConcertReservation concertReservation = new ConcertReservation(command, totalPrice, timeProvider.now()); // 공연 예약 객체 생성
        return concertWriter.save(concertReservation); // 공연 예약 저장
    }
}
