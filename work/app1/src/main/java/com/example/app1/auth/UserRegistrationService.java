package com.example.app1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    // 宣言的的トランザクション方式：コミット、ロールバックが自動で行われる
    // ランタイムエラーのサブクラスしかロールバックしてくれない
    // チェック例外、sqlエクセプションはロールバックされない。非チェック例外の時にロールバック：ランタイムエクセプション
    // @Transactional(rollbackFor = SQLException.class)とするとsqlエクセプションもロールバックできる
    public void registerUser(User user) {
        
        int result = 0;
        result += userRepository.registerUser(user);
        result += userRepository.registerUserRole(user);
        
        // USERSとUSER_ROLEが更新してない場合、ロールバック
        if (result != 2) {
            throw new RuntimeException();
        }
    }
    
    // ユーザー新規登録時のemail重複チェック
    public boolean isDupplicateEmail(String email) {
        User user = userRepository.identifyUser(email);
        // trueなら重複、falseなら重複なし
        return user != null;
    }
}
