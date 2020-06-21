package fit.se.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fit.se.main.dto.AccountCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.model.Account;
import fit.se.main.service.account.AccountService;

@Controller
@RequestMapping
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping("sign-up")
	public String signUp(AccountCreateDTO dto, Model model) {
		System.out.println("Hello");
		return "sign-up";
	}

	@PostMapping("sign-up")
	public String signUp(@Valid AccountCreateDTO accountCreateDTO, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return "sign-up";
		}
		Account account = accountService.createMember(accountCreateDTO);
		accountCreateDTO.setId(account.getAccountId());
		return "redirect:/verify-code";
	}

	@GetMapping("verify-code")
	public String verifyCode(Model model, VerifyCodeDTO verifyCodeDTO) {
		return "verify-code";
	}

	@PostMapping("verify-code")
	public String verifyCodeAction(Model model, @Valid VerifyCodeDTO verifyCodeDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "verify-code";
		}
		accountService.verifyCode(verifyCodeDTO);
		return "redirect:/sign-in";
	}
}
