package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.DmtGroupinfo;
import com.shencode.ownwebplatform.service.DmtGroupinfoService;
import com.shencode.ownwebplatform.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/dmtGroupinfo")
public class DmtGroupinfoController extends EntityController<DmtGroupinfo,Integer>{

    @Resource
    private DmtGroupinfoService dmtGroupinfoService;

    @Override
    public EntityService<DmtGroupinfo, Integer> getService() {
        return dmtGroupinfoService;
    }

    @GetMapping("/getDmtGroupinfo")
    public List<DmtGroupinfo> getDmtGroupinfo()
    {
        return dmtGroupinfoService.getAll();
    }
}
