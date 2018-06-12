package com.example.pdfmergerproject.service;

import com.example.pdfmergerproject.Dao.UserRepo;
import com.example.pdfmergerproject.Model.MergerUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void addUserToken(String userId, String userToken){
        MergerUser mergerUser = new MergerUser();
        mergerUser.setUserId(userId);
        mergerUser.setUserToken(userToken);
        mergerUser.setDeleted(false);
        userRepo.save(mergerUser);
    }

    public void deleteUserToken(String userId){
        MergerUser mergerUser = userRepo.findByUserId(userId);
        if (mergerUser != null){
            mergerUser.setDeleted(true);
            userRepo.save(mergerUser);
        }
    }

    public MergerUser getUser(String userId) {
        return userRepo.findByUserId(userId);
    }
}
