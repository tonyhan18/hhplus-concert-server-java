package kr.hhplus.concertreservation.payment.presentation.dto.response;

import kr.hhplus.concertreservation.payment.domain.model.vo.PaymentStatus;

public record PaymentResponse (
    Long paymentId,
    int paymentAmount,
    PaymentStatus status
){
    
}
