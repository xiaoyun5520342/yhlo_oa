package com.yhlo.oa.shiro.service;

import com.yhlo.oa.constant.Constants;
import com.yhlo.oa.constant.ShiroConstants;
import com.yhlo.oa.entity.SysUser;
import com.yhlo.oa.service.SysUserService;
import com.yhlo.oa.util.DateUtils;
import com.yhlo.oa.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Slf4j
@Service
public class SysLoginService {

    private Cache<String, AtomicInteger> loginRecordCache;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SysUserService userService;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    /**
     * 登录
     */
    public SysUser login(String username, String password)
    {

        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);

        if (user == null)
        {
           return null;
        }

        validate(user, password);
        recordLoginInfo(user.getUserId());
        return user;
    }

    public void validate(SysUser user, String password)
    {
        String loginName = user.getLoginName();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }


        if (!matches(user, password))
        {
            loginRecordCache.put(loginName, retryCount);
            throw new RuntimeException("用户密码不匹配!!!");
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    //验证密码是否和数据库一致
    public boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String loginName)
    {
        loginRecordCache.remove(loginName);
    }

    //加密
    public String encryptPassword(String loginName, String password, String salt)
    {
        return new Md5Hash(loginName + password + salt).toHex();
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
