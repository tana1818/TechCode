package com.example.app1.auth;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app1.common.ImageFile;

public class User implements UserDetails {
	
	private static final long serialVersionUID = -4292831594774687625L;
	// id
	private long id;
	// email
	private String email;
	// 有効開始日
	private Date avf;
	// ユーザー名
	private String username;
	// パスワード
	private String password;
	// ロックフラグ
	private boolean locked;
	// 期限切れフラグ
	private boolean expired;
	// 権限
	private List<String> roles;
	// 社員番号
    private String empNo;
    // 部署コード
    private String deptCd;
    // 部署名
    private String deptName;
    // 役職コード
    private String posCd;
    // 役職名
    private String posName;
//    // プロフィール画像
//    private ImageFile profileImage = new ImageFile();
    // プロフィール画像ファイル
    private ImageFile imageFile = new ImageFile();;

//    public ImageFile getProfileImage() {
//        return profileImage;
//    }
//
//    public void setProfileImage(ImageFile profileImage) {
//        this.profileImage = profileImage;
//    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getPosCd() {
        return posCd;
    }

    public void setPosCd(String posCd) {
        this.posCd = posCd;
    }

	public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public ImageFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(ImageFile imageFile) {
        this.imageFile = imageFile;
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// GrantedAuthorityユーザー権限を返すメソッド
		return roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !expired;
	}

	@Override
	public boolean isEnabled() {
		return !locked;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id =  id;
	}
	
	public void setEmail(String email) {
		this.email =  email;
	}
	
	public void setPassword(String password) {
		this.password =  password;
	}
	
	public void setUsername(String username) {
		this.username =  username;
	}
	
	public Date getAvf() {
		return avf;
	}
	
	public void setAvf(Date avf) {
		this.avf =  avf;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isExpired() {
		return expired;
	}
	
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void roles(List<String> roles) {
		this.roles = roles;
	}
}
