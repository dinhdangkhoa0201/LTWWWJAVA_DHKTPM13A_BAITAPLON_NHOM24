package fit.se.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fit.se.main.dto.KhachHangCreateDTO;
import fit.se.main.dto.VerifyCodeDTO;
import fit.se.main.model.KhachHang;
import fit.se.main.service.khachhang.KhachHangService;

@Controller
public class AccountController {
	@Autowired
	private KhachHangService khachHangService;
	
	@GetMapping("sign-up")
	public String signUp(KhachHangCreateDTO dto, Model model) {
		return "sign-up";
	}
	
	@PostMapping("sign-up")
	public String signUp(@Valid KhachHangCreateDTO khachHangCreateDTO, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return "sign-up";
		}
		
		KhachHang khachHang = khachHangService.createMember(khachHangCreateDTO);
		khachHangCreateDTO.setId(khachHang.getKhachhangId());
		
		return "redirect:/verify-code";
	}
	
	@GetMapping("verify-code")
	public String verifyCode(Model model, VerifyCodeDTO dto) {
		return "verify-code";
	}
	
	@PostMapping("verify-code")
	public String verifyCodeAction(Model model, @Valid VerifyCodeDTO dto, BindingResult result){
		if(result.hasErrors()) {
			return "verify-code";
		}
		
		khachHangService.verifyCode(dto);
		
		return "redirect:/sign-in";
	}
}
