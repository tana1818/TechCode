package com.example.app1.auth.account;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

public class ProfileEditForm implements Serializable {
    
    private static final long serialVersionUID = -324069659286702225L;

    private long userId;
    // アップロードファイル
    private MultipartFile uploadFile;
    // パスワード
    @Size(min = 8, max = 20)
    private String password;
    // パスワード確認用
    @Size(min = 8, max = 20)
    private String passwordConfirm;
    // 郵便番号
    @Size(min = 7, max = 7)
    private String zipcode;
    // 都道府県
    @Size(max = 8)
    private String pref;
    // 市区町村
    @Size(max = 64)
    private String city;
    // 番地建物
    @Size(max = 64)
    private String bldg;
    // 電話番号
    @Size(max = 32)
    private String phoneNo;
    // 携帯電話番号
    @Size(max = 32)
    private String mobilePhoneNo;
    
    @AssertTrue
    public boolean isPasswordConfirmed() {
        
        return ObjectUtils.nullSafeEquals(password, passwordConfirm);
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }
    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getPref() {
        return pref;
    }
    public void setPref(String pref) {
        this.pref = pref;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getBldg() {
        return bldg;
    }
    public void setBldg(String bldg) {
        this.bldg = bldg;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getMobilePhoneNo() {
        return mobilePhoneNo;
    }
    public void setMobilePhoneNo(String mobilePhoneNo) {
        this.mobilePhoneNo = mobilePhoneNo;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }


}
