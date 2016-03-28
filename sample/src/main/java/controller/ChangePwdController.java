package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.MemberInfo;
import member.MemberService;
import member.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(method = RequestMethod.GET)
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd) {
		return "edit/changePwdForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCmd, Errors errors, HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCmd, errors);
		
		if (errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
		
		try {
			memberService.changePassword(memberInfo.getEmail(), pwdCmd.getCurrentPassword(), pwdCmd.getNewPassword());
			return "edit/changedPwd";
		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
