package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.entity.Company;
import com.shencode.ownwebplatform.service.CompanyService;
import com.shencode.ownwebplatform.service.EntityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/company")
public class CompanyController extends EntityController<Company,Integer> {

    @Resource
    private CompanyService service;

    @Override
    public EntityService<Company, Integer> getService() {
        return service;
    }
}
