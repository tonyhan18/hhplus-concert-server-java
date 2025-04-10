package kr.hhplus.concertreservation.user.domain.repository;

import kr.hhplus.concertreservation.user.domain.model.entity.User;
import kr.hhplus.concertreservation.user.domain.model.entity.UserPoint;

public interface UserWriter {
    User save(User user); // 사용자 저장
    UserPoint saveUserPoint(UserPoint userPoint); // 사용자 포인트 저장
}
