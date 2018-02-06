package me.kimloong.uaa.user.service;

import me.kimloong.uaa.group.Groups;
import me.kimloong.uaa.user.Users;
import me.kimloong.uaa.user.entity.User;
import me.kimloong.uaa.user.entity.UserDetailsImpl;
import me.kimloong.uaa.user.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * 用户Security Service
 *
 * @author KimLoong
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String AT = "@";
    @Autowired
    private UserRepository repository;

    /***
     * 根据用户名获取用户
     * <pre>
     * 用户名为广义上的用户名，可能为电话号码或email，其中可能还包含有组织号
     * ex：
     * 13559205574/nd         电话号码+组织号
     * 13559205574            电话号码,组织默认为公共组织
     * kimloong@gmail.com/nd  email+组织号
     * kimloong@gmail.com     email,组织默认为公共组织
     * </pre>
     *
     * @param username 广义上的用户名
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFound
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Assert.hasText(username, "登录用户名不能为空");
        String userIdentity = paresUserIdentity(username);
        Assert.hasText(userIdentity, "登录用户名不能为空");
        String groupName = parseGroupName(username);
        Assert.hasText(groupName, "登录群组不能为空");

        User user = findUser(userIdentity, groupName);
        if (null == user) {
            throw new UsernameNotFoundException("找不到用户");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(user.getName());
        userDetails.setPassword(user.getPassword());
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        userDetails.setAuthorities(Collections.singleton(grantedAuthority));
        return userDetails;
    }

    private User findUser(String userIdentity, String groupName) {
        User user;
        if (isEmail(userIdentity)) {
            user = repository.findByEmailAndGroupsName(userIdentity, groupName);
        } else if (isMobilePhone(userIdentity)) {
            user = repository.findByMobilePhoneAndGroupsName(userIdentity, groupName);
        } else {
            user = repository.findByNameAndGroupsName(userIdentity, groupName);
        }
        return user;
    }

    private boolean isEmail(String userIdentity) {
        return StringUtils.contains(userIdentity, AT);
    }

    private boolean isMobilePhone(String userIdentity) {
        return StringUtils.isNumeric(userIdentity);
    }

    private String paresUserIdentity(String loginUsername) {
        return StringUtils.substringBefore(loginUsername, Users.GROUP_SEPARATOR);
    }

    private String parseGroupName(String loginUsername) {
        String groupName = StringUtils.substringAfter(loginUsername, Users.GROUP_SEPARATOR);
        return StringUtils.defaultIfBlank(groupName, Groups.COMMON);
    }
}
