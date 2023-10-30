package base.core.spring.rest_controller;

// 如果不在pom.xml中引入spring-boot-starter-web依赖，那么所有org.springframework.web包下的类都将无法使用。
import org.springframework.web.bind.annotation.RestController;

// Spring Boot会自动扫描@RestController注解的类，并注册为Web服务。
@RestController // @RestController注解相当于@Controller和@ResponseBody注解的结合。
public class MyRestController {
    
}
