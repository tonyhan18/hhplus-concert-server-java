package kr.hhplus.concertreservation.common.converter;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

/**
 * 클래스는 JPA에서 제공하는 AttributeConverter 인터페이스를 구현하여, 
 * Java의 List<String> 타입과 데이터베이스의 String 타입 간의 변환을 처리하는 클래스
 * 
 * AttributeConverter<X, Y> 인터페이스를 구현합니다.
 * X: 엔티티 필드의 타입 (List<String>)
 * Y: 데이터베이스 컬럼의 타입 (String)
 */
public class StringListConverter implements AttributeConverter<List<String>, String> {

    /**
     * ObjectMapper는 JSON과 Java 객체 간의 변환을 처리하는 Jackson 라이브러리의 클래스입니다.
     * 이 객체를 사용하여 List<String>을 JSON 문자열로 변환하고, 
     * JSON 문자열을 List<String>으로 변환하는 작업을 수행합니다.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * convertToDatabaseColumn 메서드는 List<String>을 JSON 문자열로 변환합니다.
     * @param dataList 변환할 List<String> 객체
     * @return 변환된 JSON 문자열
     */
    @Override
    public String convertToDatabaseColumn(List<String> dataList) {
        try {
            return mapper.writeValueAsString(dataList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * convertToEntityAttribute 메서드는 JSON 문자열을 List<String>으로 변환합니다.
     * @param data 변환할 JSON 문자열
     * @return 변환된 List<String> 객체
     */
    @Override
    public List<String> convertToEntityAttribute(String data) {
        try {
            return mapper.readValue(data, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
