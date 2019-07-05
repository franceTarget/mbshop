package com.ren.mbshop.service.impl;

import com.ren.mbshop.dao.SysConfigDao;
import com.ren.mbshop.pojo.model.SysConfig;
import com.ren.mbshop.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public SysConfig findById(String id) {
        return sysConfigDao.findById(id);
    }
}
