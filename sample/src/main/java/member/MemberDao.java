package member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;

import controller.MemberCommand;

public class MemberDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public MemberDao(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}	
	
	public void memberInsert(MemberCommand memberCommand) {
		System.out.println("[MemberDao.memberInsert()] start --");
		
		sqlSessionTemplate.insert("memberInsert", memberCommand);
		
		System.out.println("[MemberDao.memberInsert()] end --");
	}	
	
	public int dupEmailCheck(String email) {
		System.out.println("[MemberDao.dupEmailCheck()] start --");
		
		int cnt = sqlSessionTemplate.selectOne("dupEmailCheck", email);
		
		System.out.println("[MemberDao.dupEmailCheck()] end --");
		
		return cnt;
	}	

//	public String selectByEmail(String email) {
//		System.out.println("[MemberDao.selectByEmail()] start --");
//	
//		String rsEmail = sqlSessionTemplate.selectOne("selectByEmail", email);
//	
//		System.out.println("[MemberDao.selectByEmail()] end --");
//
//		return rsEmail;
//	}
	
	public Member selectByEmail(String email) {
		System.out.println("[MemberDao.selectByEmail()] start --");
		
		Member member = (Member)sqlSessionTemplate.selectOne("selectByEmail", email);
		
		System.out.println("[MemberDao.selectByEmail()] end --");

		return member;
	}
	
	public void changePassword(Member member) {
		System.out.println("[MemberDao.changePassword()] start --");
		
		sqlSessionTemplate.update("changePassword", member);
		
		System.out.println("[MemberDao.changePassword()] end --");
	}	
}
