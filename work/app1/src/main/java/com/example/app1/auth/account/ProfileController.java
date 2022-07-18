package com.example.app1.auth.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app1.auth.User;
import com.example.app1.common.ImageFile;

@Controller
public class ProfileController {
    
    @Autowired
    private ProfileService service;
    
    @GetMapping("/profile.html")
    public String profileIndex(@AuthenticationPrincipal User user,
            Model model) {
        
        ProfileEditForm form = service.providePersonalInfo(user);
        if (form == null) {
            model.addAttribute("profileEditForm", new ProfileEditForm());
        } else {
            model.addAttribute("profileEditForm", form);
        }
        if(user.getImageFile() == null) {
            ImageFile imageFile = new ImageFile();
            imageFile.setEncodedString("");
            user.setImageFile(imageFile);
        }
        model.addAttribute("user", user);
        
        return "profile.html";
    }
    
    @PostMapping("/profileEdit.do")
    public String profileEdit(@Valid @ModelAttribute ProfileEditForm profileEditForm, 
            BindingResult bindingResult, Model model, @AuthenticationPrincipal User user) {
        
        model.addAttribute("user", user);
        
        // バリデーションチェック
        if(bindingResult.hasErrors()) {
            return "profile.html";
        }
        try {
            service.editProfile(profileEditForm, user);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return "profile/Profile.html";
        }
        model.addAttribute("successMessage", "処理が正常に終了しました。");
        
        return "profile.html";
    }
    
    /**
     * 未入力項目はバリデーションの対象外とするメソッド
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
