package kr.hhplus.concertreservation.payment.domain.model.vo;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    COMPLETE("결제 완료"),
    CANCEL("결제 취소");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
