package kr.hhplus.concertreservation.payment.presentation.dto.request;

public record PaymentRequest (
    Long userId,
    Long reservationId
){
    
}
