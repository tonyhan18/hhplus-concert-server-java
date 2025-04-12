package kr.hhplus.concertreservation.common;

import java.lang.annotation.*;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * UseCase 어노테이션은 애플리케이션의 유스케이스를 나타내는 데 사용됩니다.
 * 이 어노테이션은 Spring의 Component로 등록되어, 의존성 주입을 통해 사용될 수 있습니다.
 * 
 * @UseCase 어노테이션이 붙은 클래스는 애플리케이션의 비즈니스 로직을 구현하는 유스케이스를 나타냅니다.
 * 이 어노테이션을 사용하여 유스케이스를 명확하게 구분하고, 코드의 가독성을 높일 수 있습니다.
 * 
 * RetentionPolicy.RUNTIME: 이 어노테이션은 런타임에 유지됩니다. 즉, 실행 중에도 이 어노테이션을 사용할 수 있습니다.
 * Target(ElementType.TYPE): 이 어노테이션은 클래스, 인터페이스 또는 열거형에 적용될 수 있습니다.
 * Documented: 이 어노테이션은 JavaDoc에 문서화됩니다. 즉, 이 어노테이션이 붙은 클래스는 JavaDoc에 포함됩니다.
 * Component: 이 어노테이션은 Spring의 Component로 등록되어, 의존성 주입을 통해 사용될 수 있습니다.
 * @interface : 이 어노테이션은 UseCase 어노테이션을 정의합니다.
 */
@Documented
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseCase {
    /**
     * UseCase의 이름을 지정합니다.
     * 이 속성은 @Component의 value 속성과 Alias로 사용됩니다.
     * @AliasFor를 통해 @Component의 value 속성을 @UseCase에서도 사용할 수 있도록 설정했습니다.
     * 
     * @return UseCase의 이름
     */
    @AliasFor(annotation = Component.class) 
    String value() default ""; // Component의 value 속성을 Alias로 사용합니다.
}
