package com.example.mySpingProject;

import org.springframework.stereotype.Component;

@Component
public class Grade {
	private double gpa;
	
	public Grade(){
		
	}
	
	public Grade(double gpa) {
		this.gpa = gpa;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
}
