package me.feelwith.business.service.auth;

import me.feelwith.business.data.dao.AuthTokenMapper;
import me.feelwith.business.data.entity.AuthToken;
import me.feelwith.business.data.entity.AuthTokenExample;
import me.feelwith.common.spring.DataSourceS;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.xbean.propertyeditor.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by L on 2017/2/11.
 */
@Service
@DataSourceS(dataSource = "auth",readOnly = false)
public class AuthTokenService {
    @Autowired
    AuthTokenMapper authTokenMapper;

    public String updateToken(long userId){
        AuthToken authToken = new AuthToken();
        authToken.setToken(createToken(userId));
        AuthTokenExample example = new AuthTokenExample();
        AuthTokenExample.Criteria criteria = example.createCriteria().andUserIdEqualTo(userId);
        authTokenMapper.updateByExampleSelective(authToken,example);
        return authToken.getToken();
    }

    public String insertToken(long userId){
        AuthToken authToken = new AuthToken();
        authToken.setUserId(userId);
        authToken.setToken(createToken(userId));
        authTokenMapper.insert(authToken);
        return authToken.getToken();
    }
    public String getUserToken(long userId){
        AuthToken authToken = authTokenMapper.selectByPrimaryKey(userId);
        String token = null;
        if(authToken != null){
            token = authToken.getToken();
        }
        if(token == null){
            token = insertToken(userId);
        }
        return token;
    }

    private String createToken(long userId){
        return UUID.randomUUID().toString();
    }

    public Long getUserIdByToken(String token){
        AuthTokenExample example = new AuthTokenExample();
        AuthTokenExample.Criteria criteria = example.createCriteria().andTokenEqualTo(token);
        List<AuthToken> authTokenList = authTokenMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(authTokenList)){
            return authTokenList.get(0).getUserId();
        }
        return null;
    }
}
