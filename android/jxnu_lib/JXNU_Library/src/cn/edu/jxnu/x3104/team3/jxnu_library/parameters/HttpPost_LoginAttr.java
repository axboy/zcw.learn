package cn.edu.jxnu.x3104.team3.jxnu_library.parameters;

public class HttpPost_LoginAttr {// post到服务器的数据
	/* 参数列表 */
	private String Password;// 密码
	private String StuNum;// 学生号
	
	/* 构造方法 */
	public HttpPost_LoginAttr(String password, String username) {
		Password = password;
		StuNum = username;
	}
	public String getPassword() {
		return Password;
	}

	public String getStuNum() {
		return StuNum;
	}
}
