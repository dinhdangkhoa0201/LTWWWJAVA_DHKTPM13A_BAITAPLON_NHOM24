package fit.se.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fit.se.main.model.Account;
import fit.se.main.repository.AccountRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private AccountRepository khachHangRepository;

	public String home(Model model, Pageable pageable) {
		model.addAttribute("page", khachHangRepository.findAll(pageable));
		return "layout";
	}

	@GetMapping("/pagination")
	@ResponseBody
	public Page<Account> findAll(Pageable pageable){
		return khachHangRepository.findAll(pageable);
	}

	@GetMapping("sign-in")
	public String login(Model model, HttpServletRequest request) {
		return "sign-in";
	}

	@GetMapping("index")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/sign-out")
	public String signout(HttpServletRequest request, HttpServletResponse responses) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, responses, authentication);
		}
		return "redirect:/";
	}
}
