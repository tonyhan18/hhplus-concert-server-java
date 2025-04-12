package kr.hhplus.concertreservation.concert.domain.model.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class ConcertTest {
    @Test
    public void 예약기간_내에_있는경우_참_리턴() {
        // given
        LocalDateTime now = LocalDateTime.parse("2025-04-09T10:00:00");
        LocalDateTime reservationStartAt = LocalDateTime.parse("2025-04-08T00:00:00");
        LocalDateTime reservationCloseAt = LocalDateTime.parse("2025-04-10T00:00:00");

        Concert concert = Concert.builder()
                .id(1L)
                .reservationStartAt(reservationStartAt)
                .reservationCloseAt(reservationCloseAt)
                .build();

        // when
        boolean result = concert.isWithinReservationPeriod(now);

        // Then
        assertTrue(result);
    }

    @Test
    public void 예약_오픈시간_이전인_경우_거짓_리턴() {
        // given
        LocalDateTime now = LocalDateTime.parse("2025-04-07T23:59:59");
        LocalDateTime reservationStartAt = LocalDateTime.parse("2025-04-08T00:00:00");
        LocalDateTime reservationCloseAt = LocalDateTime.parse("2024-04-10T00:00:00");

        Concert concert = Concert.builder()
                .id(1L)
                .reservationStartAt(reservationStartAt)
                .reservationCloseAt(reservationCloseAt)
                .build();

        // when
        boolean result = concert.isWithinReservationPeriod(now);

        // Then
        assertFalse(result);
    }

    @Test
    public void 예약_종료시간_이후인_경우_거짓_리턴() {
        // given
        LocalDateTime now = LocalDateTime.parse("2025-04-11T00:00:01");
        LocalDateTime reservationStartAt = LocalDateTime.parse("2025-04-09T00:00:00");
        LocalDateTime reservationCloseAt = LocalDateTime.parse("2025-04-11T00:00:00");

        Concert concert = Concert.builder()
                .id(1L)
                .reservationStartAt(reservationStartAt)
                .reservationCloseAt(reservationCloseAt)
                .build();

        // when
        boolean result = concert.isWithinReservationPeriod(now);

        // Then
        assertFalse(result);
    }
}
