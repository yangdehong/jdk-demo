package com.ydh.redsheep.bytestream;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 5364261522269840507L;
	private String name;
	private int age;

	public Student() {
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
}
