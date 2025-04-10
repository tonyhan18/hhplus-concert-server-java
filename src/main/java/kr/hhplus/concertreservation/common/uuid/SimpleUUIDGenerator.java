package kr.hhplus.concertreservation.common.uuid;

import org.springframework.stereotype.Component;
import java.util.UUID;

/*
 * UUIDGenerator을 Component로 등록하여 Spring에서 관리하도록 합니다.
 * 이 UUIDGenerator는 UUID를 생성하는 기능을 제공합니다.
 */
@Component
public class SimpleUUIDGenerator implements UUIDGenerator {
    /**
     * Generates a random UUID as a string.
     *
     * @return A randomly generated UUID in string format.
     */
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
