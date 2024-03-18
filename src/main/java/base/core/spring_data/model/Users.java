package base.core.spring_data.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
/*  javax.persistence是Java Persistence API (JPA) 的一部分，JPA是一个独立的Java ORM标准并不隶属于Spring Data。
 *  Spring Data JPA是Spring Data的一部分，它是Spring Data的一个子项目，它提供了JPA的Repository实现。
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "users") /* @Table是可选的，如果不显式标注Spring Data JPA会使用默认规则来猜测表名，包括表内的列名 */
public class Users {
    @Id /* @Id标注了实体类的属性映射为数据库的主键列 */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* @GeneratedValue标注了主键的生成策略，通过strategy属性指定 */ 
    private long id;
    
    @Column(name = "username", unique = true, nullable = false) /* constraint不需要在Entity和SQL中同时配置，二选其一即可。这里只是展示 */
    private String username;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "updatedAt") /* 如果不显式标注列名，Spring Data JPA会使用默认规则来猜测列名 */
    private LocalDateTime updatedAt;

    /* Entity需要显式无参Constructor因为ORM需要 */
    /* 这里的Getter和Setter由@Data自动完成声明 */
}