package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import member.AlreadyExistingMemberException;
import member.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/member/step1")
	public String handleStep1() {
		return "member/step1";
	}

	@RequestMapping(value = "/member/step2", method = RequestMethod.POST)
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, Model model) {
		if (!agree) {
			return "member/step1";
		}
		model.addAttribute("memberCommand", new MemberCommand());
		return "member/step2";
	}

	@RequestMapping(value = "/member/step2", method = RequestMethod.GET)
	public String handleStep2Get() {
		return "redirect:step1";
	}

	@RequestMapping(value = "/member/step3", method = RequestMethod.POST)
	public String handleStep3(MemberCommand memberCommand, Errors errors) {
		new MemberCommandValidator().validate(memberCommand, errors);
		if (errors.hasErrors())
			return "member/step2";
		try {
			System.out.println("memberCommand.getEmail() : " + memberCommand.getEmail());
			
			memberService.memberInsert(memberCommand);
			return "member/step3";
		} catch (AlreadyExistingMemberException ex) {
			errors.rejectValue("email", "duplicate");
			return "member/step2";
		}
	}
	
}
