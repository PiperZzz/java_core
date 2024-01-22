package base.core.spring_data.dao;

import base.core.spring_data.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
    List<Users> findByUpdatedAt(LocalDateTime updatedAt);
}