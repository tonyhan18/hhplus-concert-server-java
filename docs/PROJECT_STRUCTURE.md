# 주요 기술 스택

---
- **`Spring Boot 3.x`**: Java 기반의 애플리케이션을 신속하게 개발할 수 있도록 지원하는 프레임워크입니다. Spring Boot는 간단한 설정으로 애플리케이션을 빠르게 구축할 수 있으며, 내장된 톰캣 서버를 통해 별도의 서버 설정 없이 애플리케이션을 실행할 수 있습니다.
- **`JPA (Java Persistence API)`**: 객체와 관계형 데이터베이스 간의 매핑을 처리하여, 데이터베이스 작업을 객체 지향적으로 수행할 수 있습니다.

## 테스트 시 사용
- **`MySQL`**: Docker-Compose로 mysql을 띄워서 간단한 테스트 용도로 사용합니다.
- **`JUnit`**: 자바에서 널리 사용되는 테스트 프레임워크로, 단위 테스트를 작성하고 실행하는 데 사용됩니다. 각 모듈의 기능이 예상대로 동작하는지 검증합니다.
- **`Mockito`**: 자바 기반의 테스트 더블 프레임워크로, 의존성을 모의 객체로 대체하여 독립적인 단위 테스트를 할 수 있습니다.

# 패키지구조

## 구조 설명

## 주요 특징

## 학습한 내용(아키텍처)
### 1. 레이어드 아키텍처(Layered Architecture)
- 도메인 분리가 명확하고, 유지보수에 유리한 설계 패턴
#### 🔔 Rules
- 상위 계층 → 하위 계층 호출의 단방향 흐름을 유지
- 상위 계층이 필요한 기능을 하위 계층의 구현으로 전달
- 하위 계층의 변경이 상위 계층에 영향을 줄 수 있음
- 비즈니스 로직이 핵심이 아니며 보호받지 못함.
- DIP 🆗 OCP ❎

#### 기본 개념 – Layered Architecture
- Presentation(Presentation Layer) 
  - 사용자 요청 처리, UI/REST API, 컨트롤러
- Application(Business Layer) 
  - 유즈케이스, 서비스 로직
- Domain(Persistence Layter) 
  - 핵심 도메인 모델과 비즈니스 로직
- Infrastructure(Database Layer) 
  - DB, 외부 API, 결제 시스템 등 외부 자원 처리
- Common
  - 전역 예외 처리, 유틸 함수, 로깅 설정 등

## 클린 아키텍처(Clen Architecture)
#### 🔔 Rules

- 애플리케이션의 핵심은 비즈니스 로직
- 데이터 계층 및 API 계층이 비즈니스 로직을 의존 ( UseCase & Port 패턴 )
- 도메인 중심적인 고수준의 관심사 분리
- DIP 🆗 OCP 🆗