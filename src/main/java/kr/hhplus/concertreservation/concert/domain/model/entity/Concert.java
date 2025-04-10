package kr.hhplus.concertreservation.concert.domain.model.entity;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Concert {
    private Long id; // 공연 ID
    private String name; // 공연 이름
    private String plcae;
    private LocalDateTime reservationStartAt; // 예약 시작 시간
    private LocalDateTime reservationCloseAt; // 예약 종료 시간
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간

    // 예약 시작 시간과 종료 시간 사이에 있는지 확인하는 메서드
    /**
     * 현재 시간이 예약 시작 시간과 종료 시간 사이에 있는지 확인합니다.
     * @param now 현재 시간
     * @return 예약 기간 내에 있으면 true, 아니면 false
     */
    public boolean isWithinReservationPeriod(final LocalDateTime now) {
        return now.isAfter(reservationStartAt) && now.isBefore(reservationCloseAt);
    }
}
