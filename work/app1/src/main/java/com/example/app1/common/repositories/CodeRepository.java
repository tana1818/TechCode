package com.example.app1.common.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app1.common.Option;

@Mapper
public interface CodeRepository {
    /** プルダウン用リスト取得（部署コード）
     * @param
     * @return
     */
    public List<Option> getDeptCd();
    
    /** プルダウン用リスト取得（役職名）
     * @param
     * @return
     */
    public List<Option> getPosCd();

}
