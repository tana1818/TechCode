package com.example.app1.searchUser;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app1.auth.User;
import com.example.app1.auth.UserRepository;
import com.example.app1.common.repositories.CodeRepository;

@Service
public class UserSearchService {
    
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private UserRepository userRepository;
    
    // 初期表示時のフォーム設定
    public UserSearchForm initializeSearchForm() {
        UserSearchForm form = new UserSearchForm();
        form.setDeptOptions(codeRepository.getDeptCd());
        form.setPosOptions(codeRepository.getPosCd());
        return form;
    }
    
    // ユーザー情報一覧検索取得
    public List<User> getUserList(UserSearchForm form){
        form.setDeptOptions(codeRepository.getDeptCd());
        form.setPosOptions(codeRepository.getPosCd());
        // 戻り値がNullの場合は空のリストを返却
        return userRepository.getUserList(form);
    }
    
    // ユーザー情報一覧検索結果件数取得
    public int countUser(UserSearchForm form) {
        return userRepository.countUser(form);
    }
}
