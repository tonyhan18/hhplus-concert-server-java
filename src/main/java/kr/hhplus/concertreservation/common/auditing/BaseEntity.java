package kr.hhplus.concertreservation.common.auditing;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Spring Data JPA의 감사(Auditing) 기능을 활용하여 엔티티의 생성 및 수정 시간을 자동으로 관리하기 위한 기본 클래스
 * 이 클래스는 여러 엔티티에서 공통적으로 사용되는 필드를 정의하고, 이를 상속받아 재사용할 수 있도록 설계되었습니다.
 * 
 * @Getter:
 * Lombok의 @Getter를 사용하여 두 필드(createdAt, updatedAt)에 대한 getter 메서드를 자동으로 생성합니다.
 * 
 * @EntityListeners(AuditingEntityListener.class):
 * Spring Data JPA의 AuditingEntityListener를 사용하여 엔티티의 라이프사이클 이벤트(생성, 수정 등)를 감지하고, 자동으로 필드 값을 설정합니다.
 * 
 * @MappedSuperclass: 
 * 이 클래스는 데이터베이스 테이블과 직접 매핑되지 않으며, 이를 상속받는 하위 클래스에서만 필드와 매핑 정보를 사용할 수 있습니다.
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    /**
     * createdAt 필드는 엔티티가 처음 생성된 시간을 나타냅니다.
     * @CreatedDate는 Spring Data JPA가 엔티티가 저장될 때(INSERT) 자동으로 현재 시간을 설정하도록 합니다.
     */
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    /**
     * updatedAt 필드는 엔티티가 마지막으로 수정된 시간을 나타냅니다.
     * @LastModifiedDate는 엔티티가 업데이트될 때(UPDATE) 자동으로 현재 시간을 설정합니다.
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;
}

