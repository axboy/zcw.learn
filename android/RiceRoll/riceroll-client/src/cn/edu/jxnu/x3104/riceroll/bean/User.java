package cn.edu.jxnu.x3104.riceroll.bean;

public class User {
	private String userId;
	private String userName;
	private String userPassWord;
	private String userAddress;
	private String userTel;
	private String userRegDate;

	/**构造函数*/
	public User(String userName, String userPassWord,String userTel,String userAddress) {
		super();
		this.userName = userName;
		this.userPassWord = userPassWord;
		this.userAddress = userAddress;
		this.userTel = userTel;
	}	

	public User(String userId, String userName, String userPassWord,
			String userAddress, String userTel, String userRegDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassWord = userPassWord;
		this.userAddress = userAddress;
		this.userTel = userTel;
		this.userRegDate = userRegDate;
	}
	
	public String getUserNickName() {
		return userName;
	}
	public void setUserNickName(String userNickName) {
		this.userName = userNickName;
	}
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserRegDate() {
		return userRegDate;
	}


	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}

	
}