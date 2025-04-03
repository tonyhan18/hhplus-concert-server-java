# 콘서트 예약 시스템 API 명세

## 목차
1. [대기열 토큰 발급](#1-대기열-토큰-발급)
2. [대기열 정보 조회](#2-대기열-정보-조회)
3. [결제](#3-결제)
4. [잔액 충전](#4-잔액-충전)
5. [잔액 조회](#5-잔액-조회)
6. [콘서트 세션(일정) 조회](#6-콘서트-일정-조회)
7. [콘서트 좌석 조회](#7-콘서트-좌석-조회)
8. [좌석 예약](#8-좌석-예약)


## 1. 대기열 토큰 발급

---
### Description
- 사용자의 대기열 토큰을 발급합니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/queuetoken`
- **Headers**:
  - `Content-Type`: application/json
- **Request Body**:
  ```json
  {
    "userId": "8a463aa4-b1dc-4f27-9c3f-53b94dc45e74"
  }
  ```

### Response
- **Status Code**: 201
- **Response Body**:
  ```json
  {
    "id": 1,
    "token" : "abcdefg",
    "joinedAt": "2025-04-04 09:00:00"
  }
  ```

### Error
- 사용자가 존재하지 않는 경우
  - **Status Code**: 404


<br>

## 2. 대기열 정보 조회

---
### Description
- 사용자의 대기열 정보를 조회합니다.
- 폴링으로 대기열 정보를 조회하기 위해 사용됩니다.

### Request
- **Method**: GET
- **URL**: `/api/v1/queuetoken/users/{userId}`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)

### Response
- **Status Code**: 200
- **Response Body**:
  ```json
  {
    "id": 1,
    "userId": 1,
    "status": "WAITING",
    "waitingNumber": 123
  }
  ```


### Error
- 유효하지 않은 토큰인 경우
  - **Status Code**: 401
- 유저가 존재하지 않는 경우
  - **Status Code**: 404

<br>

<br>

## 3. 결제

---
### Description
- 예약에 대한 결제를 진행합니다.
- 예약된 상태일 때만 결제가 가능합니다.
- 결제가 완료되면 해당 좌석의 소유권을 유저에게 부여하고 대기열 토큰을 만료시킵니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/reservations/{reservationId}/payments`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)
- **Request Body**:
```json
{
  "userId": 1
}
```

### Response
- **Status Code**: 200
- **Response Body**:
```json
{
  "paymentId": 1,
  "paymentAmount": 20000,
  "status": "COMPLETED"
}
```

### Error
- 유효하지 않은 토큰인 경우
  - **Status Code**: 401
- 결제할 예약이 존재하지 않는 경우
  - **Status Code**: 404
- 결제할 예약이 예약 상태가 아니라면
  - **Status Code**: 400
- 결제할 예약의 유저가 요청한 유저와 다른 경우
  - **Status Code**: 400
- 결제할 예약의 좌석이 예약 상태가 아니라면
  - **Status Code**: 400
- 잔액이 부족한 경우
  - **Status Code**: 400

<br>

## 4. 잔액 충전

---
### Description
- 사용자의 잔액을 충전합니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/users/{userId}/pointaccount/recharge`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)
- **URL Params**:
    - `userId`: Long (사용자 ID)
- **Body**:
  ```json
  {
    "amount": 1000
  }
  ```

### Response
- **Status Code**: 200
- **Body**:
  ```json
  {
    "totalAmount": 2000
  }
  ```

### Error
- 사용자가 존재하지 않는 경우
  - **Status Code**: 404
- 충전 금액이 유효하지 않은 경우
  - **Status Code**: 400

<br>

## 5. 잔액 조회

---
### Description
- 사용자의 현재 잔액을 조회합니다.

### Request
- **Method**: `GET`
- **URL**: `/api/v1/users/{userId}/pointaccount`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)
- **Path Params**:
    - `userId`: Long (사용자 ID)

### Response
- **Status Code**: 200
- **Response Body**:
  ```json
  {
    "totalAmount": 20000
  }
  ```

### Error
```json
{
  "code": 404,
  "message": "user not found"
}
```

## 6. 콘서트 일정 조회

---
### Description
- 특정 콘서트의 예약 가능한 세션(일정) 조회합니다.
- 예약 가능한 회차 조건은 다음과 같습니다.
  - 콘서트 예약 오픈 날짜가 지났으면서 콘서트 일시가 지나지 않은 경우
  - 좌석이 매진이어도 조회 가능합니다.

### Request
- **Method**: GET
- **URL**: `/api/v1/concerts/{concertId}/rounds`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)
- **Path Params**:
    - `concertId`: Long (콘서트 ID)

### Response
- **Status Code**: 200
- **Response Body**:
  ```json
  {
    "rounds": [
      {
        "roundId": 1,
        "concertAt": "2025-04-04 12:00:00"
      },
      {
        "roundId": 2,
        "concertAt": "2025-08-04 13:00:00"
      }
    ]
  }
  ```

### Error
- 유효하지 않은 토큰인 경우
  - **Status Code**: 401
- 콘서트가 존재하지 않는 경우
  - **Status Code**: 404

<br>

## 7. 콘서트 좌석 조회

---
### Description
- 특정 콘서트 세션의 좌석 정보를 조회합니다.
  - 예약 가능한 좌석 목록과 가능하지 않은 좌석 목록을 분리하여 반환합니다.

### Request
- **Method**: GET
- **URL**: `/api/v1/concerts/{concertId}/rounds/{roundId}/seats`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)
- **Path Params**:
    - `concertId`: Long (콘서트 ID)
    - `roundId`: Long (콘서트 회차 ID)

### Response
- **Status Code**: 200
- **Response Body**:
  - seatId 구성 : concertId-
  ```json
  {
    "totalSeatCount": 200,
    "unavailableSeats": [
        {
            "seatId": 10,
            "number": "19",
            "status": "RESERVED",
            "price": 10000
        },
        {
            "seatId": 11,
            "number": "21",
            "status": "TEMPORARY_RESERVED",
            "price": 10000
        }
    ],
    "availableSeats": [
        {
            "seatId": 102,
            "number": "20",
            "status": "AVAILABLE",
            "price": 10000
        },
        {
            "seatId": 104,
            "number": "22",
            "status": "AVAILABLE",
            "price": 10000
        }
    ]
  }
  ```

### Error
- 유효하지 않은 토큰인 경우
  - **Status Code**: 401
- 콘서트가 존재하지 않는 경우
  - **Status Code**: 404
- 콘서트 세션이 존재하지 않는 경우
  - **Status Code**: 404

<br>

## 8. 좌석 예약

---
### Description
- 특정 콘서트 세션의 좌석을 예약합니다.
  - 좌석을 여러 개 예약할 수 있습니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/reservations`
- **Headers**:
  - `Content-Type`: application/json
  - `Queue-Token`: String (대기열 토큰)
- **Body**:
```json
{
  "userId": 1,
  "concertId": 1,
  "roundId": 1,
  "seatIds": [10, 11]
}
```

### Response
- **Status Code**: 201
- **Response Body**:
  ```json
  {
    "reservationId": 1,
    "totalPrice": 20000,
    "reservedSeats": [
      {
        "seatId": 10,
        "number": "10",
        "price": 10000
      },
      {
        "seatId": 11,
        "number": "11",
        "price": 10000
      }
    ]
  }
  ```

### Error
- 유효하지 않은 토큰인 경우
  - **Status Code**: 401
- 콘서트가 존재하지 않는 경우
  - **Status Code**: 404
- 콘서트 세션이 존재하지 않는 경우
  - **Status Code**: 404
- 좌석이 매진인 경우
  - **Status Code**: 400