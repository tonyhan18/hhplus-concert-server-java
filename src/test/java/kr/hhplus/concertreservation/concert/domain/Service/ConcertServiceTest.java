package kr.hhplus.concertreservation.concert.domain.Service;

import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import kr.hhplus.concertreservation.common.time.TimeProvider;
import kr.hhplus.concertreservation.concert.domain.exception.ConcertSeatUnavailableException;
import kr.hhplus.concertreservation.concert.domain.exception.NotConcertReservationPeriodException;
import kr.hhplus.concertreservation.concert.domain.model.dto.ReserveConcertCommand;
import kr.hhplus.concertreservation.concert.domain.model.entity.Concert;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertReservation;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertSeat;
import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertReservationStatus;
import kr.hhplus.concertreservation.concert.domain.model.vo.ConcertSeatStatus;
import kr.hhplus.concertreservation.concert.domain.repository.ConcertReader;
import kr.hhplus.concertreservation.concert.domain.repository.ConcertWriter;
import kr.hhplus.concertreservation.concert.domain.service.ConcertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock
    private ConcertWriter concertWriter;

    @Mock
    private ConcertReader concertReader;

    @Mock
    private TimeProvider timeProvider;

    @InjectMocks
    private ConcertService concertService;

    @Test
    void 콘서트_예약_성공() {
        // given
        ReserveConcertCommand command = new ReserveConcertCommand(1L, 1L, 1L, List.of(1L, 2L));

        Concert concert = mock(Concert.class);
        given(concertReader.getConcertById(1L)).willReturn(concert);
        given(concert.isWithinReservationPeriod(any())).willReturn(true);

        ConcertSeat concertSeat1 = ConcertSeat.builder()
                .id(1L)
                .price(10000)
                .status(ConcertSeatStatus.AVAILABLE)
                .build();

        ConcertSeat concertSeat2 = ConcertSeat.builder()
                .id(2L)
                .price(20000)
                .status(ConcertSeatStatus.AVAILABLE)
                .build();

        given(concertReader.getConcertSeatById(1L)).willReturn(concertSeat1);
        given(concertReader.getConcertSeatById(2L)).willReturn(concertSeat2);

        ConcertReservation concertReservation = new ConcertReservation(command, 30000, LocalDateTime.now());
        given(concertWriter.save(any())).willReturn(concertReservation);

        // when
        final ConcertReservation result = concertService.reserveConcert(command);

        // then
        assertAll(
                () -> assertEquals(30000, result.getTotalPrice()),
                () -> assertEquals(2, result.getSeatIds().size()),
                () -> assertEquals(ConcertReservationStatus.TEMPORARY_RESERVED, result.getStatus())
        );
    }

    @Test
    void 콘서트_예약이_불가능한_기간이라면_예외가_발생한다() {
        // given
        ReserveConcertCommand command = new ReserveConcertCommand(1L, 1L, 1L, List.of(1L, 2L));

        Concert concert = mock(Concert.class);
        given(concertReader.getConcertById(1L)).willReturn(concert);
        given(concert.isWithinReservationPeriod(any())).willReturn(false);

        // when & then
        thenThrownBy(() -> concertService.reserveConcert(command))
                .isInstanceOf(NotConcertReservationPeriodException.class)
                .hasMessage("예약 가능한 기간이 아닙니다.");
    }

    @Test
    void 콘서트_좌석이_예약_불가능하다면_예외가_발생한다() {
        // given
        ReserveConcertCommand command = new ReserveConcertCommand(1L, 1L, 1L, List.of(1L, 2L));

        Concert concert = mock(Concert.class);
        given(concertReader.getConcertById(1L)).willReturn(concert);
        given(concert.isWithinReservationPeriod(any())).willReturn(true);

        ConcertSeat concertSeat1 = ConcertSeat.builder()
                .id(1L)
                .price(10000)
                .status(ConcertSeatStatus.TEMPORARY_RESERVED)
                .build();

        ConcertSeat concertSeat2 = ConcertSeat.builder()
                .id(2L)
                .price(20000)
                .status(ConcertSeatStatus.AVAILABLE)
                .build();

        given(concertReader.getConcertSeatById(1L)).willReturn(concertSeat1);
        given(concertReader.getConcertSeatById(2L)).willReturn(concertSeat2);

        // when & then
        thenThrownBy(() -> concertService.reserveConcert(command))
                .isInstanceOf(ConcertSeatUnavailableException.class)
                .hasMessage("예약 가능한 좌석이 아닙니다.");
    }
}