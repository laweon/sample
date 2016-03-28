package member;

public class Member {
	private String id;
	private String email;
	private String password;
	private String name;
	private String regDate;
	
	public Member() {
	}

	public Member(String id, String email, String password, String name, String regDate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void changePassword(String oldPwd, String newPwd) {
		if (!password.equals(oldPwd))
			throw new IdPasswordNotMatchingException();
		this.password = newPwd;
	}

	public boolean matchPassword(String pwd) {
		return this.password.equals(pwd);
	}

}
