package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.DmtNodeinfo;
import com.shencode.ownwebplatform.service.DmtNodeinfoService;
import com.shencode.ownwebplatform.service.EntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/dmtNodeinfo")
public class DmtNodeinfoController extends  EntityController<DmtNodeinfo,Integer> {

    @Resource
    private DmtNodeinfoService dmtNodeinfoService;

    @Override
    public EntityService<DmtNodeinfo, Integer> getService() {
        return dmtNodeinfoService;
    }

    @GetMapping("getDmtNodeinfo")
    public List<DmtNodeinfo> getDmtNodeinfo()
    {
        return dmtNodeinfoService.getAll();
    }
}
