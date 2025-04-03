```mermaid
---
config:
  theme: default
---
erDiagram
    User ||--o{ QueueToken : owns
    User ||--|| PointAccount : has
    User ||--o{ SeatReservation : makes
    User ||--o{ Payment : makes
    SeatReservation ||--|| Payment : paid_by
    Concert ||--o{ ConcertRound : includes
    ConcertRound ||--o{ Seat : contains
    Seat ||--o{ SeatReservation : reserved_by
    User {
        string id PK "사용자 고유 ID (Primary Key)"
        string name "사용자 이름"
        string email "사용자 이메일"
    }
    PointAccount {
        string userId PK "사용자 ID (User.id), 1:1 연결"
        int balance "보유 포인트"
        datetime lastUpdated "마지막 업데이트 시각"
    }
    QueueToken {
        string id PK "토큰 고유 ID"
        string userId "토큰 소유자 ID (User.id)"
        string status "토큰 상태 (대기중, 활성 등)"
        datetime issuedAt "토큰 발급 시각"
        datetime activateAt "토큰 활성 시각"
        datetime expiresAt "토큰 만료 시각"
    }
    SeatReservation {
        string id PK "예약 고유 ID"
        string userId "예약자 ID (User.id)"
        string status "예약 상태 (대기, 확정, 취소 등)"
        string seatId "좌석 ID (Seat.id)"
        datetime reservedAt "예약 시간"
        datetime expiresAt "예약 만료 시간"
        
        int usedPoints "사용된 포인트"
        string paymentId "결제 ID (Payment.id), 결제와 연결"
    }
    Payment {
        string id PK "결제 고유 ID"
        string userId "결제한 사용자 ID (User.id)"
        int amount "결제 금액"
        string status "결제 상태 (성공, 실패, 취소)"
        datetime paidAt "결제 완료 시간"
    }
    Concert {
        string id PK "공연 고유 ID"
        string title "공연 제목"
        string description "공연 설명"
        datetime reservationOpenAt "예약 오픈 일시"
        datetime reservationCloseAt "예약 마감 일시"
    }
    ConcertRound {
        string id PK "공연 회차 ID"
        string concertId "공연 ID (Concert.id)"
        datetime startTime "회차 시작 시간"
        int totalSeatCnt "총 좌석 수"
    }
    Seat {
        string id PK "좌석 고유 ID"
        string concertRoundId "공연 회차 ID (ConcertRound.id)"
        string seatNumber "좌석 번호"
        int price "좌석 가격"
        string status "좌석 상태 (예매 가능, 예매 완료 등)"
    }
```

<Description>

### 다수의 좌석을 예약을 뺀 이유
우선은 1:1 맵핑으로 기능을 구현하고
추후에 1:N의 좌석을 예약할 수 있도록 하고자 함. 수정한다면 SeatReservation의 seatId를 복수로 만들고자 함

### pk에 string을 쓴 이유
- UUID 또는 고유 문자열 ID 사용 전제
- 분산 시스템에서 중복 방지
- 프론트와 백엔드 통신 시 일관성 유지

### userId와 PointAccount 관계
완벽한 1:1 관계로 만들기 위해 PointAccount에는 userId를 PK로 넣음

### QueueToken
대기열이 하나뿐이기 때문에 Token의 Id를 PK로 사용

</Description>