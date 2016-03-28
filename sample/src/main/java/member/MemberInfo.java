package member;

public class MemberInfo {

	private String id;
	private String email;
	private String name;

	public MemberInfo(String id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

}
