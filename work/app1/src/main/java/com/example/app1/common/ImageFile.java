package com.example.app1.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * ファイルシステムからファイルを取り出すクラス
 */
public class ImageFile {
    
    private String fileName;
    
    private String encodedString;
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }
    
    public void encode(MultipartFile multipartFile) throws IllegalStateException, IOException {
        fileName = multipartFile.getOriginalFilename();
        multipartFile.transferTo(Paths.get(multipartFile.getOriginalFilename()));
    }
    
    /**
     * BASE64でエンコードしたファイルデータを文字列で返す
     * BASE64はJava１.8から導入されたエンコード
     * @return
     */
    public String getEncodedString() {
        // ファイルパスの取得
        if (StringUtils.hasText(encodedString)){
            // プロフィール画像が登録済みの場合
            return encodedString;
        }
        if (!StringUtils.hasText(fileName)){
            // プロフィール画像が未登録の場合
            fileName = getClass().getResource("/static/img/profile.png").getPath();
        }
        
        // ファイルパスよりファイルオブジェクト作成
        File imageFile = new File(fileName);
        StringBuffer base64String = new StringBuffer();
        try {
            // ファイルのタイプ（jpeg形式ならimage/jpeg、png形式だったらimage/pngのような文字列を取得）
            String contentType = Files.probeContentType(imageFile.toPath());
            // ファイルの内容をバイト配列として呼び出している
            byte[] fileData = Files.readAllBytes(imageFile.toPath());
            
            // 戻り値となる文字列を生成している
            base64String.append("data:");
            base64String.append(contentType);
            base64String.append(";base64,");
            // BASE64形式でエンコードしている
            base64String.append(Base64.getEncoder().encodeToString(fileData));
        } catch(IOException e) {
            return "";
        }
        encodedString = base64String.toString();
        return encodedString;
    }
}
