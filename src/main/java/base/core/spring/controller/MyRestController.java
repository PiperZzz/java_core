package base.core.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
// 如果不在pom.xml中引入spring-boot-starter-web依赖，那么所有org.springframework.web包下的类都将无法使用。
import org.springframework.web.bind.annotation.RestController;

// Spring Boot会自动扫描@RestController注解的类，并注册为Web服务。
@RestController // @RestController注解相当于@Controller和@ResponseBody注解的结合。
public class MyRestController {

    // @Autowired
    // private ResourceService resourceService;

    
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

    @GetMapping("/api/resource/{id}")
    public ResponseEntity<String> getResourceById(@PathVariable Long id) {
        // 处理GET请求，根据ID返回资源
        return ResponseEntity.ok("Resource with ID " + id);
    }

    @GetMapping("/api/search")
    public ResponseEntity<String> searchResource(@RequestParam String query) {
        // 处理GET请求，根据查询参数返回搜索结果
        return ResponseEntity.ok("Search results for query: " + query);
    }

    @PostMapping("/api/resource")
    public ResponseEntity<String> createResource(@RequestBody Resource resourceDTO) {
        // 处理POST请求，创建资源
        // resourceDTO包含请求体数据
        return ResponseEntity.ok("Resource created with data: " + resourceDTO);
    }

    // @ExceptionHandler(ResourceNotFoundException.class)
    // public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
    //     // 处理资源未找到异常
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
    // }


}
