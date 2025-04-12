package kr.hhplus.concertreservation.payment.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.hhplus.concertreservation.payment.presentation.dto.response.PaymentResponse;
import kr.hhplus.concertreservation.payment.domain.model.vo.PaymentStatus;
import kr.hhplus.concertreservation.payment.presentation.dto.request.PaymentRequest;

@RestController
@RequestMapping("/api/v1/payments")
// PaymentController 클래스는 결제 관련 API를 처리하는 컨트롤러입니다.
public class PaymentController {
    @PostMapping
    // 결제 요청을 처리하는 메서드입니다.
    public ResponseEntity<PaymentResponse> payReservation(
            @RequestBody PaymentRequest request
    ) {
        // 결제 처리 로직을 여기에 추가합니다.
        // 예시로, 결제 ID와 금액을 포함한 PaymentResponse 객체를 생성하여 반환합니다.
        PaymentResponse payReservationResponse = new PaymentResponse(1L, 100, PaymentStatus.COMPLETE);
        return ResponseEntity.ok(payReservationResponse);
    }
}
