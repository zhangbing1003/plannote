package com.ooice.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 2811824991402178346L;
	
	private int id;
    private int state;
    private String name;
    
    private String password;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
  
    
}
