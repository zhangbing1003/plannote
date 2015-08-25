package com.ooice.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ooice.dao.UserMapper;
import com.ooice.model.User;

@Service
public class UserService {
	
	 private UserMapper userMapper;
	 
	 public int insertUser(User user) {
	        return userMapper.insertUser(user);
	    }

	    public User selectUser(User user){
	    	 return userMapper.selectUser(user);
	    }
	    
	    public int updateUser(User user){
	    	int i = 0;
	    	
	    	i = userMapper.updateUser(user);
	    	
	    	userMapper.deleteUser(2);
	    
	    	
	    	return i;
	    }
	    
	    public int deleteUser(Integer userId){
	    	return userMapper.deleteUser(userId);
	    }
	    
	    public List<User> selectUserList(){
	    	
	    	return userMapper.selectUserList();
	    }

		public UserMapper getUserMapper() {
			return userMapper;
		}

		@Resource
		public void setUserMapper(UserMapper userMapper) {
			this.userMapper = userMapper;
		}
		
		

}
