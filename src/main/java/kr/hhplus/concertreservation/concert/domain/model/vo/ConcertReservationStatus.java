package kr.hhplus.concertreservation.concert.domain.model.vo;

import lombok.Getter;

@Getter
public enum ConcertReservationStatus {
    TEMPORARY_RESERVED("임시 예약"),
    PAYMENT_COMPLETED("결제 완료"),
    CANCELED("예약 취소");

    private final String value;

    ConcertReservationStatus(String value) {
        this.value = value;
    }
}
