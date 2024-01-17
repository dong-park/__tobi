package dongpark.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {

    /**
     * <b>현재 애플리케이션 컨텍스트에서 추가적으로 가져올 구성 클래스들의 이름을 반환합니다.</b>
     * <br>
     * 사용 예로, 특정 클래스 경로에 특정 라이브러리가 존재하는 경우에만 특정 구성 클래스를 로드하고 싶을 때 DeferredImportSelector를 사용할 수 있습니다.
     * 이는 클래스 경로 스캐닝과 같은 비용이 많이 드는 작업을 최소화하고, 애플리케이션의 시작 시간을 개선하는 데 도움이 됩니다.
     * 요약하자면, DeferredImportSelector는 스프링 프레임워크에서 조건부 로직을 사용하여 구성 클래스를 지연시켜 로드하는 고급 기능을 제공합니다.
     * 이를 통해 애플리케이션의 성능을 최적화하고, 필요에 따라 보다 세밀하게 구성을 제어할 수 있습니다.
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties.class.getName());
//        Class propertyClass = (Class) attr.getFirst("value");

        List<Object> propertyClasses = attr.get("value");
        String[] classNames = new String[propertyClasses.size()];
        for (int i = 0; i < propertyClasses.size(); i++) {
            classNames[i] = ((Class) propertyClasses.get(i)).getName();
        }
        return classNames;
    }
}

