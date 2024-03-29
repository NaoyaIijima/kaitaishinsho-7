package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao dao;
    
    /**
     * insert用メソッド.
     */
    public boolean insert(User user) {

        // insert実行
        int rowNumber = dao.insertOne(user);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }

        return result;
    }
    
    // カウント用メソッド
    public int count(){
        return dao.count();
    }
    
    // 全件取得用メソッド
    public List<User> selectMany(){
        return dao.selectMany();
    }
    
    // 1件取得用メソッド
    public User selectOne(String userId){
        return dao.selectOne(userId);
    }
    
    // 1件更新メソッド
    public boolean updateOne(User user){
        
        // 1件更新
        int rowNumber = dao.updateOne(user);
        
        boolean result = false;
        
        if(rowNumber > 0){
            result = true;
        }
        
        return result;
        
    }
    
    // 1件削除用メソッド
    public boolean deleteOne(String userId){
        
        int rowNumber = dao.deleteOne(userId);
        
        boolean result = false;
        
        if(rowNumber > 0){
            result = true;
        }
        
        return result;
        
    }
    
}