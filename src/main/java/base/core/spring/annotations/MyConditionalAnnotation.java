package base.core.spring.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;

@Configuration
@PropertySource("classpath:application.properties") 
@ConditionalOnClass({base.core.spring.controllers.MyRestController.class, base.core.CoreApplication.class})
public class MyConditionalAnnotation {

    class MyDumbieBeanIfExist {}

    class MyDumbieBeanIfMissing {}

    @Bean
    @ConditionalOnBean(base.core.spring.controllers.MyRestController.class)
    public MyDumbieBeanIfExist myDumbieBeanIfExist() {
        return new MyDumbieBeanIfExist();
    }

    @Bean
    @ConditionalOnMissingBean(base.core.spring.controllers.MyRestController.class)
    public MyDumbieBeanIfMissing myDumbieBeanIfMissing() {
        return new MyDumbieBeanIfMissing();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "update")
    public MyDumbieBeanIfExist myDumbieBeanIfPropertyIsTrue() {
        return new MyDumbieBeanIfExist();
    }
}
