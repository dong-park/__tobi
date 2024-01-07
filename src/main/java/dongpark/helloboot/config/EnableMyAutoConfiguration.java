package dongpark.helloboot.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration {

}
