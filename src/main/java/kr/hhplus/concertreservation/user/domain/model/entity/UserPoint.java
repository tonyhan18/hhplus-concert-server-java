package kr.hhplus.concertreservation.user.domain.model.entity;

import kr.hhplus.concertreservation.user.domain.exception.PointAmountInvalidException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * UserPoint 클래스는 사용자의 포인트 정보를 나타내는 클래스입니다.
 * 이 클래스는 사용자 ID, 포인트 잔액 및 포인트 충전/사용 메서드를 포함합니다.
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPoint {
    private Long id; // 사용자 포인트 ID
    private Long userId; // 사용자 ID
    private Long amount; // 포인트 잔액

    public void charge(final long amount)
    {
        if (amount < 0) {
            throw new PointAmountInvalidException("충전하려는 포인트는 0보다 커야 합니다.");
        }
        this.amount += amount;
    }

    public void use(final long amount)
    {
        if (amount < 0) {
            throw new PointAmountInvalidException("포인트 사용 금액은 0보다 커야 합니다.");
        }
        if (this.amount < amount) {
            throw new PointAmountInvalidException("잔여 포인트가 부족합니다.");
        }
        this.amount -= amount;
    }
}
