package base.core.spring.rest_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// 如果不在pom.xml中引入spring-boot-starter-web依赖，那么所有org.springframework.web包下的类都将无法使用。
import org.springframework.web.bind.annotation.RestController;

// Spring Boot会自动扫描@RestController注解的类，并注册为Web服务。
@RestController // @RestController注解相当于@Controller和@ResponseBody注解的结合。
public class MyRestController {
    
    @GetMapping("/api/resource")
    public ResponseEntity<String> getResource() {
        // 处理GET请求，返回资源
        return ResponseEntity.ok("This is a GET request response.");
    }

    @PostMapping("/api/resource")
    public ResponseEntity<String> createResource(@RequestBody String data) {
        // 处理POST请求，创建资源
        // data包含POST请求的数据
        return ResponseEntity.ok("Resource created with data: " + data);
    }

}
