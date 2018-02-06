package me.kimloong.uaa.user.service;

import me.kimloong.uaa.user.repository.BelongToRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组织/用户从属关系Service
 *
 * @author KimLoong
 */
//@Service
public class BelongToService {

    @Autowired
    private BelongToRepository repository;

//    public boolean isBelongTo(String userId, String organizationCode) {
//        return repository.isBelongTo(userId,organizationCode);
//    }
}
