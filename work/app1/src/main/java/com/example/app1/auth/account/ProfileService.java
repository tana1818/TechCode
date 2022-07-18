package com.example.app1.auth.account;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.app1.auth.User;
import com.example.app1.auth.UserRepository;
import com.example.app1.common.ImageFile;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public static final String PROFILE_IMAGE_DKST = "/Users/tana18/work_file/img/00_profile";

    public ProfileEditForm providePersonalInfo(User user) {
        // プロフィール情報の検索
        ProfileEditForm profileInfo = userRepository.createProfileEditForm(user.getId());
        
        return profileInfo;
    }
    // 
    @Transactional(rollbackFor = Throwable.class)
    public void editProfile(ProfileEditForm profileEditForm, User user) throws Exception {
        MultipartFile uploadFie = profileEditForm.getUploadFile();
        String filePath = PROFILE_IMAGE_DKST  + File.separator + user.getEmpNo() + File.separator;
        // ファイルアップロードが存在する場合
        if (!uploadFie.isEmpty()) {
            File directory = new File(filePath);
            // 既にアップロードファイルが存在する場合
            if (directory.exists()) {
                // ディレクトリ配下のファイル全て
                for (File target : directory.listFiles()) {
                    // 過去のファイルの削除
                    target.delete();
                }
            } else {
                // mkdirsメソッドはもしフォルダがなかったら作成してくれる
                directory.mkdirs();
            }
        }
        // ファイルの更新があった場合
        if(!uploadFie.getOriginalFilename().isEmpty()) {
            // ファイルシステム上に作成するファイル
            File dest = new File(filePath + uploadFie.getOriginalFilename());
            // ファイルシステムに書き出し（IllegalStateException, IOExceptionが必要）
            uploadFie.transferTo(dest);
            
            ImageFile imageFile = new ImageFile();
            // getAbsolutePath()はファイルパス＋ファイル名取得
            imageFile.setFileName(dest.getAbsolutePath());
            user.setImageFile(imageFile);
        }
        
        int result = 0;
        
        // User情報の更新
        // パスワードの更新があった場合
        if (profileEditForm.getPassword() != null && !profileEditForm.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(profileEditForm.getPassword()));
        }
        result += userRepository.updateUser(user);
        
        // プロフィール情報の更新
        profileEditForm.setUserId(user.getId());
        result += userRepository.updateProfileInfo(profileEditForm);
        
        // 更新に失敗していたらロールバック
        if (result != 2) {
            throw new Exception();
        }
    }

}
