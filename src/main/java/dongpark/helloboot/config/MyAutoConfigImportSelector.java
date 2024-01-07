package dongpark.helloboot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "dongpark.helloboot.config.autoconfig.DispatcherServletConfig",
                "dongpark.helloboot.config.autoconfig.HelloServiceConfig"
        };
    }
}
