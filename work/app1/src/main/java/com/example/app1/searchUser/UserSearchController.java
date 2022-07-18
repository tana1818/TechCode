package com.example.app1.searchUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app1.auth.User;
import com.example.app1.common.SearchResult;

@Controller
@Scope("session")
public class UserSearchController {
    
    @Autowired
    private UserSearchService service;
    
    private UserSearchForm form;
    
    public static final int PAGE_LIMIT = 3;
    
    // ユーザー情報一覧画面初期表示
    @GetMapping("/userSearch.html")
    public String onUserListRequested(Model model) {
        
        model.addAttribute("userSearchForm", service.initializeSearchForm());
        
        return "userSearch.html";
    }
    
    // 検索処理
    @GetMapping("/userSearch.do")
    public String onSearchRequested(@ModelAttribute UserSearchForm form, Model model) {
        
        this.form = form;
        
        form.setPageFrom(0);
        form.setCount(PAGE_LIMIT);
        
        SearchResult<User> searchResult = new SearchResult<>(service.countUser(form), PAGE_LIMIT);
        searchResult.moveTo(1);
        searchResult.setEntities(service.getUserList(form));
        
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("userSearchForm", form);
        
        return "userSearch.html";
    }
    
    // ユーザー情報一覧画面ページング
    @GetMapping("/userPageView.do")
    public String onPageViewRequested(@RequestParam("p") int pageNo, Model model) {
        
        model.addAttribute("userSearchForm", form);
        
        SearchResult<User> searchResult = new SearchResult<>(service.countUser(form), PAGE_LIMIT);
        if (pageNo < 1 || pageNo > searchResult.getTotalPageCount()) {
            return "userSearch.html";
        }
        searchResult.moveTo(pageNo);
        form.setPageFrom((pageNo - 1) * PAGE_LIMIT);
        searchResult.setEntities(service.getUserList(form));
        model.addAttribute("searchResult", searchResult);
        
        return "userSearch.html";
    }
}