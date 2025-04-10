package kr.hhplus.concertreservation.user.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.concertreservation.user.presentation.dto.request.ChargeUserPointsRequest;
import kr.hhplus.concertreservation.user.presentation.dto.response.ChargeUserPointsResponse;
import kr.hhplus.concertreservation.user.presentation.dto.response.GetUserPointsResponse;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    // 잔액 충전
    @PostMapping("/{userId}/points/charge")
    public ResponseEntity<ChargeUserPointsResponse> chargePoints(
            @PathVariable Long userId,
            @RequestBody ChargeUserPointsRequest request
    ) {
        ChargeUserPointsResponse response = new ChargeUserPointsResponse(1000);
        return ResponseEntity.ok(response);
    }

    // 잔액 조회
    @GetMapping("/{userId}/Points")
    public ResponseEntity<GetUserPointsResponse> getPoints(
            @PathVariable Long userId
    ) {
        GetUserPointsResponse response = new GetUserPointsResponse(1000);
        return ResponseEntity.ok(response);
    }
}
