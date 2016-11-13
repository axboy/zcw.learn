package cn.wazitang.learn.domain;

import java.io.Serializable;

/**
 * 测试domain类，暂不写入数据库，实现Serializable接口，是为了可以将实例保存到session
 * @author zcw
 */
public class User implements Serializable{

	private static final long serialVersionUID = 4261450032243563780L;

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
