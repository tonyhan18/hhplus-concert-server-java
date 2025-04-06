package kr.hhplus.concertreservation.user.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.concertreservation.user.presentation.dto.request.ChargeUserpointaccountRequest;
import kr.hhplus.concertreservation.user.presentation.dto.response.ChargeUserpointaccountResponse;
import kr.hhplus.concertreservation.user.presentation.dto.response.GetUserpointaccountResponse;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    // 잔액 충전
    @PostMapping("/{userId}/pointaccount/charge")
    public ResponseEntity<ChargeUserpointaccountResponse> chargepointaccount(
            @PathVariable Long userId,
            @RequestBody ChargeUserpointaccountRequest request
    ) {
        ChargeUserpointaccountResponse response = new ChargeUserpointaccountResponse(1000);
        return ResponseEntity.ok(response);
    }

    // 잔액 조회
    @GetMapping("/{userId}/pointaccount")
    public ResponseEntity<GetUserpointaccountResponse> getpointaccount(
            @PathVariable Long userId
    ) {
        GetUserpointaccountResponse response = new GetUserpointaccountResponse(1000);
        return ResponseEntity.ok(response);
    }
}
