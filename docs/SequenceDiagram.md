## 목록
1. [대기열 발급 요청](#1-대기열-발급-요청)
2. [대기열 조회 요청](#2-대기열-조회-요청)
3. [콘서트 예약 가능 회차(날짜) 조회](#3-콘서트-예약-가능-회차-조회)
4. [콘서트 회차 좌석 조회](#4-콘서트-회차-좌석-조회)
5. [콘서트 회차의 좌석 예약](#5-콘서트-회차의-좌석-예약)
6. [결제](#6-결제)
7. [잔액 충전](#7-잔액-충전)
8. [잔액 조회](#8-잔액-조회)
9. [대기열 활성/만료 스케줄링](#9-대기열-활성만료-스케줄링)
10. [임시 예약 좌석 만료 스케줄링](#10-임시-예약-좌석-만료-스케줄링)

## 1. 대기열 발급 요청

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant AuthServer
    participant TokenService
    participant Queue

    User->>Frontend: 콘서트 예매 요청
    activate Frontend
    Frontend->>AuthServer: 사용자 ID 전송
    activate AuthServer
    AuthServer->>AuthServer: 사용자 ID 확인
    alt 사용자 존재
        AuthServer-->>Frontend: 인증 성공
        deactivate AuthServer
        Frontend->>TokenService: 토큰 발급 요청
        activate TokenService
        TokenService->>TokenService: 토큰 생성
        TokenService-->>Frontend: 토큰 전달
        deactivate TokenService
        Frontend->>Queue: 토큰 Queue에 삽입(대기 상태)
        activate Queue
        Queue-->>Frontend: 신규 대기열 반환
        deactivate Queue
        Frontend-->>User: 예매 대기 중 알림
    else 사용자 없음
        AuthServer-->>Frontend: 인증 실패
        Frontend-->>User: 사용자 없음 알림
        deactivate Frontend
    end

```

### Description
대기열 토큰 발급 요청에 대한 시퀀스 다이어그램.

1. 사용자가 대기자 목록에 들어가 위해서 우선 사용자가 존재하는지 검사합니다.
2. 사용자가 존재하지 않을 경우 에러 메시지를 반환합니다.
3. 대기열 새로 토큰 발급하여 대기열에 저장합니다. 이때, 대기열 상태는 '대기'로 저장.
4. 대기열 정보를 사용자에게 반환.


## 2. 대기열 조회 요청

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant TokenService
    participant Queue

    activate User
    User->>Frontend: 대기열 상태 조회 요청 (토큰 포함)
    deactivate User

    activate Frontend
    Frontend->>TokenService: 토큰 유효성 검사 요청
    deactivate Frontend

    activate TokenService
    TokenService->>TokenService: 토큰 검증
    alt 토큰 유효함
        TokenService-->>Frontend: 유효함
        deactivate TokenService

        activate Frontend
        Frontend->>Queue: 대기열 정보 요청 (토큰)
        deactivate Frontend

        activate Queue
        Queue-->>Frontend: 대기열 상태 정보
        deactivate Queue

        activate Frontend
        Frontend-->>User: 대기열 정보 전달
        deactivate Frontend
    else 토큰 무효
        TokenService-->>Frontend: 유효하지 않음

        activate Frontend
        Frontend-->>User: 에러 메시지 (유효하지 않은 토큰)
        deactivate Frontend
    end
```

### Description
대기열 정보 조회 요청에 대한 시퀀스 다이어그램입니다.

- 사용자가 대기열 정보 조회 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
- 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
- 대기열 정보를 조회하여 대기 순서, 상태 등의 정보를 사용자에게 반환합니다.


## 3. 콘서트 예약 가능 회차 조회

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant Queue
    participant Concert

    activate User
    User->>Frontend: 예약 가능 회차 조회 요청 (토큰 포함)
    deactivate User

    activate Frontend
    Frontend->>Queue: 토큰 유효성 확인 요청
    deactivate Frontend

    activate Queue
    Queue->>Queue: 토큰 검증
    alt 토큰 유효
        Queue-->>Frontend: 유효한 토큰
        deactivate Queue

        activate Frontend
        Frontend->>Concert: 예약 가능 회차 목록 요청
        deactivate Frontend

        activate Concert
        Concert-->>Frontend: 예약 가능 회차 목록
        deactivate Concert

        activate Frontend
        Frontend-->>User: 회차 목록 전달
        deactivate Frontend
    else 토큰 무효 or 비활성화
        Queue-->>Frontend: 에러 메시지 (토큰 오류 또는 비활성화)

        activate Frontend
        Frontend-->>User: 에러 메시지 전달
        deactivate Frontend
    end
```

### Description
콘서트 예약 가능 날짜 조회 요청에 대한 시퀀스 다이어그램입니다.

- Queue의 역할은 명확하게 토큰을 관리하게 됩니다.
- Concert의 역할은 콘서트 예약을 관리하게 됩니다.
- 사용자가 대기열 토큰을 포함해 예약 가능한 회차를 조회 요청하면(full loop), 시스템은 해당 토큰이 유효한지 확인합니다.
- 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
- 토큰이 활성 상태가 아닌 경우 에러 메시지를 반환합니다.
- 예약 가능한 회차 목록을 조회하여 사용자에게 반환합니다.
- 예약 가능한 회차 목록은 콘서트 일시, 잔여 좌석 수 등의 정보를 포함합니다.


## 4. 콘서트 회차 좌석 조회

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant Queue
    participant Concert

    activate User
    User->>Frontend: 좌석 조회 요청 (토큰)
    deactivate User

    activate Frontend
    Frontend->>Queue: 토큰 유효성 확인 요청
    deactivate Frontend

    activate Queue
    Queue->>Queue: 토큰 검증
    alt 토큰 유효
        Queue-->>Frontend: 유효한 토큰
        deactivate Queue

        activate Frontend
        Frontend->>Concert: 좌석 정보 요청
        deactivate Frontend

        activate Concert
        Concert-->>Frontend: 좌석 정보 반환
        deactivate Concert

        activate Frontend
        Frontend-->>User: 좌석 정보 전달
        deactivate Frontend
    else 토큰 무효 or 비활성화
        Queue-->>Frontend: 에러 메시지 (토큰 오류 또는 비활성화)

        activate Frontend
        Frontend-->>User: 에러 메시지 전달
        deactivate Frontend
    end
```

### Description
콘서트 회차의 좌석 조회 요청에 대한 시퀀스 다이어그램입니다.

- 사용자가 대기열 토큰을 포함해 특정 회차의 좌석을 조회 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
  - 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
  - 토큰이 활성 상태가 아닌 경우 에러 메시지를 반환합니다.
- 특정 회차의 좌석 목록을 조회하여 사용자에게 반환합니다.
- 좌석 목록은 좌석 번호, 가격, 상태 등의 정보를 포함합니다.
- 좌석 상태에는 '예약됨', '예약 가능', '판매 완료' 등으로 구분됩니다.


## 5. 콘서트 회차의 좌석 예약

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant Queue
    participant Concert

    activate User
    User->>Frontend: 좌석 예약 요청 (토큰, 회차 ID, 좌석 ID)
    deactivate User

    activate Frontend
    Frontend->>Queue: 토큰 유효성 확인 요청
    deactivate Frontend

    activate Queue
    Queue->>Queue: 토큰 검증
    alt 토큰 유효
        Queue-->>Frontend: 유효한 토큰
        deactivate Queue

        activate Frontend
        Frontend->>Concert: 좌석 예약 요청 (회차 ID, 좌석 ID)
        deactivate Frontend

        activate Concert
        Concert->>Concert: 좌석 가능 여부 확인
        alt 좌석 예약 가능
            Concert->>Concert: 좌석 상태 "예약됨"으로 변경
            Concert-->>Frontend: 예약 성공 응답
            deactivate Concert

            activate Frontend
            Frontend-->>User: 예약 완료 알림
            deactivate Frontend
        else 좌석 예약 불가 (이미 예약됨 등)
            Concert-->>Frontend: 예약 실패 응답 (사유 포함)
            activate Frontend
            Frontend-->>User: 예약 실패 알림
            deactivate Frontend
        end
    else 토큰 무효 or 비활성화
        Queue-->>Frontend: 에러 메시지 (토큰 오류 또는 비활성화)

        activate Frontend
        Frontend-->>User: 에러 메시지 전달
        deactivate Frontend
    end
```

### Description
콘서트 회차의 좌석 예약 요청에 대한 시퀀스 다이어그램입니다.

- 사용자가 대기열 토큰을 포함해 특정 회차의 좌석을 예약 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
  - 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
  - 토큰이 활성 상태가 아닌 경우 에러 메시지를 반환합니다.
- 좌석이 이미 예약된 경우 에러 메시지를 반환합니다.
- 좌석이 예약 가능한 경우, 좌석을 임시 예약하고 사용자에게 성공 메시지를 반환합니다.
- 좌석 임시 예약은 다른 사용자가 예약할 수 없도록 잠금 처리됩니다.

## 6. 결제

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant Queue
    participant Concert
    participant PointService
    participant Payment

    activate User
    User->>Frontend: 결제 요청 (토큰, 회차 ID, 좌석 ID, 결제 정보)
    deactivate User

    activate Frontend
    Frontend->>Queue: 토큰 유효성 확인 요청
    deactivate Frontend

    activate Queue
    Queue->>Queue: 토큰 검증
    alt 토큰 유효
        Queue-->>Frontend: 유효한 토큰
        deactivate Queue

        activate Frontend
        Frontend->>Concert: 좌석 상태 확인 요청 (회차 ID, 좌석 ID)
        deactivate Frontend

        activate Concert
        Concert->>Concert: 좌석 임시 예약 상태 확인
        alt 임시 예약 상태일 경우
            Concert-->>Frontend: 확인 완료
            deactivate Concert

            activate Frontend
            Frontend->>PointService: 포인트 잔액 확인 요청
            deactivate Frontend

            activate PointService
            PointService->>PointService: 포인트 검증
            alt 포인트 충분
                PointService-->>Frontend: 사용 가능
                deactivate PointService

                activate Frontend
                Frontend->>Payment: 결제 요청
                deactivate Frontend

                activate Payment
                Payment->>Payment: 결제 처리
                alt 결제 성공
                    Payment-->>Frontend: 결제 성공
                    deactivate Payment

                    activate Frontend
                    Frontend->>Concert: 좌석 상태 "결제 완료"로 변경 요청
                    deactivate Frontend

                    activate Concert
                    Concert-->>Frontend: 상태 변경 완료
                    deactivate Concert

                    activate Frontend
                    Frontend->>Queue: 토큰 상태를 "소진"으로 변경
                    deactivate Frontend

                    activate Queue
                    Queue-->>Frontend: 토큰 만료 처리 완료
                    deactivate Queue

                    activate Frontend
                    Frontend-->>User: 결제 완료 알림
                    deactivate Frontend
                else 결제 실패
                    Payment-->>Frontend: 결제 실패

                    activate Frontend
                    Frontend-->>User: 결제 실패 알림
                    deactivate Frontend
                end
            else 포인트 부족
                PointService-->>Frontend: 포인트 부족

                activate Frontend
                Frontend-->>User: 결제 불가 (포인트 부족)
                deactivate Frontend
            end
        else 좌석 임시 예약 아님
            Concert-->>Frontend: 예약 불가 상태

            activate Frontend
            Frontend-->>User: 결제 불가 (좌석 상태 오류)
            deactivate Frontend
        end
    else 토큰 무효 or 비활성화
        Queue-->>Frontend: 에러 메시지 (토큰 오류 또는 비활성화)

        activate Frontend
        Frontend-->>User: 에러 메시지 전달
        deactivate Frontend
    end

```

### Description
결제 요청에 대한 시퀀스 다이어그램입니다.

- 사용자가 대기열 토큰을 포함해 예약한 좌석을 결제 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
  - 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
- 결제 요청을 받은 시스템은 결제 진행전 마지막으로 좌성 상태를 체크합니다. 그 사이 예약 상태가 변할 수 있는 금융 행위이기 때문입니다.
- 결재 요청을 받은 시스템은 사용자의 잔액을 차감합니다.
  - 결재 요청을 잔액이 충분하지 않은 경우 에러 메시지를 반환합니다.
  - 잔액이 충분한 경우 결제 내역을 저장하고, 좌석 예약을 확정합니다.
- 결제 완료 후, 대기열 토큰을 만료 처리합니다.
- 결제 완료 정보를 사용자에게 반환합니다.

## 7. 잔액 충전

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant Queue
    participant PointService

    activate User
    User->>Frontend: 포인트 충전 요청 (토큰, 충전 금액)
    deactivate User

    activate Frontend
    Frontend->>Queue: 토큰 유효성 확인 요청
    deactivate Frontend

    activate Queue
    Queue->>Queue: 토큰 검증
    alt 토큰 유효
        Queue-->>Frontend: 유효한 토큰
        deactivate Queue

        activate Frontend
        Frontend->>PointService: 포인트 적립 요청 (사용자 ID, 충전 금액)
        deactivate Frontend

        activate PointService
        PointService->>PointService: 사용자 계정에 포인트 추가
        PointService-->>Frontend: 적립 완료
        deactivate PointService

        activate Frontend
        Frontend-->>User: 포인트 충전 완료 알림
        deactivate Frontend
    else 토큰 무효 or 비활성화
        Queue-->>Frontend: 토큰 오류 응답

        activate Frontend
        Frontend-->>User: 충전 실패 (인증 오류)
        deactivate Frontend
    end
```

### Description
잔액 충전 요청에 대한 시퀀스 다이어그램입니다.

- 사용자 토큰이 유효한지 
- 사용자가 잔액 충전 요청을 하면, 시스템은 충전 금액이 유효한지 확인합니다.
  - 충전 금액이 유효하지 않은 경우 에러 메시지를 반환합니다.
  - 충전 금액이 유효한 경우, 사용자의 잔액을 충전하고, 총 잔액 정보를 반환합니다.

## 8. 잔액 조회

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant Frontend
    participant Queue
    participant PointService

    activate User
    User->>Frontend: 포인트 잔액 조회 요청 (토큰 포함)
    deactivate User

    activate Frontend
    Frontend->>Queue: 토큰 유효성 확인 요청
    deactivate Frontend

    activate Queue
    Queue->>Queue: 토큰 검증
    alt 토큰 유효
        Queue-->>Frontend: 유효한 토큰
        deactivate Queue

        activate Frontend
        Frontend->>PointService: 포인트 잔액 조회 요청 (사용자 ID)
        deactivate Frontend

        activate PointService
        PointService->>PointService: 사용자 계정에서 포인트 잔액 조회
        PointService-->>Frontend: 포인트 잔액 반환
        deactivate PointService

        activate Frontend
        Frontend-->>User: 포인트 잔액 전달
        deactivate Frontend
    else 토큰 무효 or 비활성화
        Queue-->>Frontend: 에러 메시지 (토큰 오류 또는 비활성화)

        activate Frontend
        Frontend-->>User: 에러 메시지 전달
        deactivate Frontend
    end
```
### Description
잔액 조회 요청에 대한 시퀀스 다이어그램입니다.

- 잔액조회를 위해 사용자 토큰을 우선 검증합니다.
  - 유효하지 않은 토큰이라면 에러 메시지를 반환합니다
- 사용자가 잔액 조회 요청을 하면, 시스템은 사용자의 총 잔액 정보를 반환합니다.
- 총 잔액 정보를 사용자에게 반환합니다.


## 9. 대기열 활성/만료 스케줄링

`대기열 토큰 활성`

```mermaid
sequenceDiagram
    autonumber
    participant Scheduler
    participant Queue
    participant TokenService
    participant User

    activate Scheduler
    Scheduler->>Queue: 다음 입장 대상자 목록 요청
    deactivate Scheduler

    activate Queue
    Queue->>Queue: 대기열 정렬 및 유효 사용자 필터링
    Queue-->>Scheduler: 입장 대상 사용자 목록 (예: Top N명)
    deactivate Queue

    loop 각 사용자에 대해
        activate Scheduler
        Scheduler->>TokenService: 토큰 발급 요청 (사용자 ID, 만료 시간 포함)
        deactivate Scheduler

        activate TokenService
        TokenService->>TokenService: 토큰 생성 및 TTL 설정
        TokenService-->>Scheduler: 토큰 발급 완료
        deactivate TokenService

        activate Scheduler
        Scheduler->>Queue: 토큰 발급 사용자 상태 "활성"으로 변경
        Scheduler-->>User: 입장 가능 알림 (토큰 포함)
        deactivate Scheduler
    end
```

`대기열 활성 토큰 만료`
```mermaid
sequenceDiagram
    autonumber
    participant Scheduler
    participant Queue
    participant User

    activate Scheduler
    Scheduler->>Queue: 만료 토큰 조회 요청 (현재 시간 기준)
    deactivate Scheduler

    activate Queue
    Queue->>Queue: 토큰 목록에서 만료 시간 확인
    alt 만료된 토큰 존재
        Queue-->>Scheduler: 만료 대상 토큰 목록
        deactivate Queue

        loop 각 만료 토큰
            activate Scheduler
            Scheduler->>Queue: 토큰 상태를 "EXPIRED"로 변경
            deactivate Scheduler

            activate Queue
            Queue-->>User: 토큰 만료 알림 (선택사항)
            deactivate Queue
        end
    end
```

### Description
대기열 토큰 활성/만료 스케줄링에 대한 시퀀스 다이어그램입니다.

- `대기열 토큰 활성`
  - 일정 시간마다 대기 중인 대기열 N 개를 활성 상태로 변경합니다.
- `대기열 활성 토큰 만료`
  - 일정 시간마다 활성 토큰을 만료 처리합니다.
  - 만료시킬 기준은 활성 일시를 기준으로 합니다.
  - 활성 일시가 N분 이상 지난 토큰은 만료 처리합니다.
  - 마지막 액션 시간이 N분 이상 지난 활성 토큰은 만료 처리합니다.


## 10. 임시 예약 좌석 만료 스케줄링

```mermaid
sequenceDiagram
    autonumber
    participant Scheduler
    participant Concert
    participant User

    activate Scheduler
    Scheduler->>Concert: 임시 예약 좌석 만료 대상 조회 요청 (현재 시간 기준)
    deactivate Scheduler

    activate Concert
    Concert->>Concert: 임시 예약 좌석의 만료 시간 검사
    alt 만료된 임시 예약 있음
        Concert-->>Scheduler: 만료된 좌석 목록
        deactivate Concert

        loop 각 만료 좌석
            activate Scheduler
            Scheduler->>Concert: 좌석 상태를 "예약 가능"으로 변경
            deactivate Scheduler

            activate Concert
            Concert-->>User: 임시 예약 만료 알림 (선택사항)
            deactivate Concert
        end
    else 만료 없음
        Concert-->>Scheduler: 없음

    end
```

### Description
임시 예약 좌석 만료 스케줄링에 대한 시퀀스 다이어그램입니다.

일정 시간마다 임시 예약된 좌석을 만료 처리합니다.
만료 조건은 5분동안 결제가 완료되지 않은 경우입니다.
결제 일시 기준으로 5분이 지난 임시 예약 좌석은 만료 처리합니다.
