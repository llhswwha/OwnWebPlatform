package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.DmtDevinfo;
import com.shencode.ownwebplatform.service.DmtDevinfoService;
import com.shencode.ownwebplatform.service.EntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dmtDevinfo")
public class DmtDevinfoController extends EntityController<DmtDevinfo,Integer> {
    @Resource
    private DmtDevinfoService dmtDevinfoService;

    @Override
    public EntityService<DmtDevinfo, Integer> getService() {
        return dmtDevinfoService;
    }

    //测试用
    @GetMapping("/getDmtDevinfo")
    public List<DmtDevinfo> getDmtDevinfo()
    {
        return dmtDevinfoService.getAll();
    }

}
