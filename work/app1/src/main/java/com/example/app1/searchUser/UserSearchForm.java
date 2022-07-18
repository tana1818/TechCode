package com.example.app1.searchUser;

import java.util.List;

import com.example.app1.common.Option;

public class UserSearchForm {
    // 社員番号
    private String empNo;
    // ユーザー名
    private String name;
    // 部署コード
    private String deptCd;
    // 役職名
    private String posCd;
    // プルダウン（部署コード）
    private List<Option> deptOptions;
    // プルダウン（役職名）
    private List<Option> posOptions;
    // ページングFROM
    private int pageFrom;
    // 総件数
    private int count;
    
    public String getEmpNo() {
        return empNo;
    }
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public List<Option> getDeptOptions() {
        return deptOptions;
    }
    public void setDeptOptions(List<Option> deptOptions) {
        this.deptOptions = deptOptions;
    }
    public List<Option> getPosOptions() {
        return posOptions;
    }
    public void setPosOptions(List<Option> posOptions) {
        this.posOptions = posOptions;
    }
    public int getPageFrom() {
        return pageFrom;
    }
    public void setPageFrom(int pageFrom) {
        this.pageFrom = pageFrom;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
