package member;

import org.springframework.transaction.annotation.Transactional;

import controller.MemberCommand;
import member.AlreadyExistingMemberException;

public class MemberService {
	private MemberDao memberDao;

	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}	
	
	@Transactional
	public void memberInsert(MemberCommand memberCommand) {
		System.out.println("MemberService.memberCommand.getEmail() : " + memberCommand.getEmail());
		int cnt = memberDao.dupEmailCheck(memberCommand.getEmail());
		if (cnt > 0) {
			throw new AlreadyExistingMemberException("dup email" + memberCommand.getEmail());
		} 
		
		memberDao.memberInsert(memberCommand);
	}	
	
//	@Transactional
//	public MemberInfo authenticate(String email, String password) {
////		Member member = memberDao.selectByEmail(email);
//		String rsEmail = memberDao.selectByEmail(email);
//		
//		if (rsEmail == null) {
//			throw new IdPasswordNotMatchingException();
//		}
////		if (!member.matchPassword(password)) {
////			throw new IdPasswordNotMatchingException();
////		}
//		
//		return new MemberInfo("", rsEmail, "");
//	}		
	
	@Transactional
	public MemberInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		
		if (member == null) {
			throw new IdPasswordNotMatchingException();
		}
		if (!member.matchPassword(password)) {
			throw new IdPasswordNotMatchingException();
		}
		
		return new MemberInfo(member.getId(), member.getEmail(), member.getName());
	}	
	
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.changePassword(member);
	}	
}
