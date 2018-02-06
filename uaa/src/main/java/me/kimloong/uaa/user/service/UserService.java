package me.kimloong.uaa.user.service;

import me.kimloong.uaa.group.entity.Group;
import me.kimloong.uaa.group.service.GroupService;
import me.kimloong.uaa.user.entity.BelongTo;
import me.kimloong.uaa.user.entity.User;
import me.kimloong.uaa.user.repository.BelongToRepository;
import me.kimloong.uaa.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 *
 * @author KimLoong
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BelongToRepository belongToRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User add(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        repository.save(user);

        Group commonGroup = groupService.findCommon();
        BelongTo belongTo = new BelongTo();
        belongTo.setGroup(commonGroup);
        belongTo.setUser(user);
        belongToRepository.save(belongTo);

        return user;
    }
}
