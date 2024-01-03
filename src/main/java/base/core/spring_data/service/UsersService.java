package base.core.spring_data.service;

import java.util.List;

import base.core.spring_data.model.Users;
import base.core.spring_data.dao.UsersRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional /* 用来担保该方法内所有操作要么全部成功，要么全部失败 */
    public void saveUsers(List<Users> usersList) {
        for (Users users : usersList) {
            usersRepository.save(users);
        }
    }

    public void saveAllUsers(List<Users> usersList) {
        usersRepository.saveAll(usersList);
    }
}