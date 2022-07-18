package com.example.app1.common.repositories;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NumberingRepository {
    // 社員番号の最大値取得
    public String numberingEmpNo();
}
