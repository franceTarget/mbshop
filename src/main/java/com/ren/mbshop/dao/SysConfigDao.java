package com.ren.mbshop.dao;

import com.ren.mbshop.pojo.model.SysConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysConfigDao {

    SysConfig findById(@Param("id") String id);
}
