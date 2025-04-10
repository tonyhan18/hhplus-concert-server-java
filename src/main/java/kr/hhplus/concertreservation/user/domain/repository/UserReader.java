package kr.hhplus.concertreservation.user.domain.repository;

import kr.hhplus.concertreservation.user.domain.model.entity.User;
import kr.hhplus.concertreservation.user.domain.model.entity.UserPoint;

public interface UserReader {
    User getById(Long userId); // 사용자 ID로 사용자 조회
    boolean existsById(Long userId); // 사용자 ID로 사용자 존재 여부 확인
    UserPoint getUserPointByUserId(Long userId); // 사용자 ID로 사용자 포인트 조회
}
