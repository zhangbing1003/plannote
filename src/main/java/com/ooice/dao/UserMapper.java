package com.ooice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ooice.model.User;


public interface UserMapper {

	/**
     * user
     * @param user
     * @return
     */
    public int insertUser(User user);
    
    
    public User selectUser(User user);
    
    
    public int updateUser(User user);
    
    public int deleteUser(Integer userId);
    
    public List<User> selectUserList();
    
    
    
}
