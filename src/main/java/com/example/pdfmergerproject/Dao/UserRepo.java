package com.example.pdfmergerproject.Dao;

import com.example.pdfmergerproject.Model.MergerUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<MergerUser, String> {
    MergerUser findByUserId(String userId);
}
