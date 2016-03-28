package member;

public class AlreadyExistingMemberException extends RuntimeException {

	public AlreadyExistingMemberException(String message) {
		super(message);
	}

}