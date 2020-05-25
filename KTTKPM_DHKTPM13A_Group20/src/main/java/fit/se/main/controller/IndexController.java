package fit.se.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fit.se.main.model.KhachHang;
import fit.se.main.repository.KhachHangRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private KhachHangRepository khachHangRepository;
	
	public String home(Model model, Pageable pageable) {
		model.addAttribute("page", khachHangRepository.findAll(pageable));
		return "layout";
	}
	
	@GetMapping("/pagination")
	@ResponseBody
	public Page<KhachHang> findAll(Pageable pageable){
		return khachHangRepository.findAll(pageable);
	}
	
	@GetMapping("sign-in")
	public String login(Model model, HttpServletRequest request) {
		return "sign-in";
	}
}
