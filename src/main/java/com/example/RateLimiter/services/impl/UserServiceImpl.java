package com.example.RateLimiter.services.impl;

import com.example.RateLimiter.constants.ApplicationConstants;
import com.example.RateLimiter.models.User;
import com.example.RateLimiter.repositories.UserRepository;
import com.example.RateLimiter.services.UserService;
import com.example.RateLimiter.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public String register(User user) {
        userRepository.save(user);
        return JWTUtils.createJwt(user.getEmail()+"-+-"+user.getEncryptedPass());
    }

    @Override
    public String login(User user) {
        Optional<User> optionalUser= userRepository.findById(user.getEmail());
        if(optionalUser.isEmpty())
            return "no such user with this email";
        else
        {
            if(optionalUser.get().getEncryptedPass().equals(user.getEncryptedPass()))
                return JWTUtils.createJwt(user.getEmail()+"-+-"+user.getEncryptedPass());
            else
                return "Wrong password- remember passwords are case sensitive";
        }
    }

    @Override
    public String verifyUserAndRefreshToken(String auth) {
        Claims claims=JWTUtils.extractSubject(auth);
        if(JWTUtils.isExpired(claims))
            return "expired token";
        else
        {
            String subject=claims.getSubject();
            String[] info=subject.split("-\\+-");
            if(info.length==2)
            {
                String email=info[0];
                String encryptedPass=info[1];
                Optional<User> user=userRepository.findById(email);
                if(user.isPresent())
                {
                    if(user.get().getEncryptedPass().equals(encryptedPass))
                        return ApplicationConstants.TOKEN_VALIDATED+"-"+JWTUtils.refreshJWT(auth);
                }
            }
            return ApplicationConstants.INVALID_TOKEN;
        }
    }

    @Override
    public String getEmailFromVerifiedToken(String auth)
    {
        Claims claims= JWTUtils.extractSubject(auth);
        String[] userInfo=claims.getSubject().split("-\\+-");
        return userInfo[0];
    }

}
