package com.cartisan.management.modules.sys.shiro;

import com.cartisan.management.modules.sys.gateway.MenuClient;
import com.cartisan.management.modules.sys.gateway.UserClient;
import com.cartisan.management.modules.sys.model.Menu;
import com.cartisan.management.modules.sys.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * <p>Title: UserRealm</p>
 * <p>Description: 认证 </p>
 *
 * @author colin
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserClient userClient;

    @Autowired
    private MenuClient menuClient;

    /**
     * 授权（验证权限时调用）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        final User user = (User) principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        List<String> permsList;

        if (userId == 1) {
            List<Menu> menuList = menuClient.getAll();
            permsList = menuList.stream().map(menu -> menu.getPerms()).collect(Collectors.toList());

        }
        else{
            permsList = userClient.getAllPerms(userId);
        }

        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isEmpty(perms)) {
                continue;
            }
            permsSet.addAll(asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证（登录时调用）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        final User user = userClient.getUser(token.getUsername());

        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确。");
        }

        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定，请联系管理员。");
        }

        final SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());

        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        final HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
