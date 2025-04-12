package kr.hhplus.concertreservation.concert.application.usecase;

import kr.hhplus.concertreservation.common.UseCase;
import kr.hhplus.concertreservation.concert.domain.model.dto.ReserveConcertCommand;
import kr.hhplus.concertreservation.concert.domain.model.entity.ConcertReservation;
import kr.hhplus.concertreservation.concert.domain.service.ConcertService;
import kr.hhplus.concertreservation.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReserveConcertUseCase {
    private final ConcertService concertService;
    private final UserService userService;

    public ConcertReservation reserveConcert(final ReserveConcertCommand command) {
        userService.checkUserExists(command.userId()); // 사용자 존재 여부 확인
        return concertService.reserveConcert(command); // 공연 예약 처리
    }
}
