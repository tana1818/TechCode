package com.example.app1;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import com.example.app1.auth.User;
import com.example.app1.auth.UserRegistrationService;
import com.example.app1.auth.form.UserRegistrationForm;
import com.example.app1.common.repositories.NumberingRepository;

@Controller
public class RootController {
	
	@Autowired
	private UserRegistrationService userRegistrationervice;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private NumberingRepository numberingRepository;
	@Autowired
    private MessageSource messagesource;

	@GetMapping("/")
	public String root() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String entryPoint() {
		return "login";
	}

	
	@GetMapping("/registration")
	public String signup(Model model) {
		model.addAttribute("userRegistrationForm", new UserRegistrationForm());
		
		return "signup";
	}
	
	@PostMapping("/signin-error")
	public String onAuthenticationError(@RequestAttribute("SPRING_SECURITY_LAST_EXCEPTION")AuthenticationException ex,
			Model model) {
		model.addAttribute("authenticationException", ex);
		return "login";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute UserRegistrationForm userRegistrationForm,
			BindingResult bindingResult, Model model) {
		
		// バリデーションチェック
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		// Emailの重複チェック
		if(userRegistrationervice.isDupplicateEmail(userRegistrationForm.getEmail())) {
		    String emailDup = messagesource.getMessage("emailDup.param", new String[] {
		            userRegistrationForm.getEmail()}, Locale.getDefault());
		    // エラーメッセージ：{0}は既に登録済みのE-Mailです。
			model.addAttribute("emailDup", emailDup);
			return "signup";
		}
		// ユーザー登録処理
		User user = new User();
		user.setEmail(userRegistrationForm.getEmail());
		user.setUsername(userRegistrationForm.getUsername());
		user.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
		// プロフィール画面で使用する社員番号の設定
		// ユーザー情報の社員番号の最大値の取得
		String maxEmpNo = numberingRepository.numberingEmpNo();
		if(maxEmpNo == null) {
		    user.setEmpNo("00000001");
		} else {
		    int numberingEmpNo = Integer.parseInt(maxEmpNo) + 1;
		    user.setEmpNo(String.format("%08d", numberingEmpNo));
		}
		// ユーザー情報の登録
		userRegistrationervice.registerUser(user);
		
		return "login";
	}
}
