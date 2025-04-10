package kr.hhplus.concertreservation.user.domain.service;

import org.springframework.stereotype.Service;

import kr.hhplus.concertreservation.user.domain.exception.UserNotFoundException;
import kr.hhplus.concertreservation.user.domain.model.entity.UserPoint;
import kr.hhplus.concertreservation.user.domain.repository.UserReader;
import kr.hhplus.concertreservation.user.domain.repository.UserWriter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserWriter userWriter;

    public void checkUserExists(final Long userId) {
        if (!userReader.existsById(userId)) {
            throw new UserNotFoundException();
        }
    }

    public UserPoint chargePoint(final Long userId, final Long amount) {
        checkUserExists(userId);
        final UserPoint userPoint = userReader.getUserPointByUserId(userId);
        userPoint.charge(amount);
        return userWriter.saveUserPoint(userPoint);
    }

    public UserPoint usePoint(final Long userId, final long amount) {
        checkUserExists(userId);
        final UserPoint userPoint = userReader.getUserPointByUserId(userId);
        userPoint.use(amount);
        return userReader.getUserPointByUserId(userId);
    }
}
