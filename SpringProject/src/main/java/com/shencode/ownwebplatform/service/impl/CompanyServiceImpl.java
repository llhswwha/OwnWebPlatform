package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Company;
import com.shencode.ownwebplatform.repository.CompanyRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.CompanyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyServiceImpl extends EntityServiceImpl<Company> implements CompanyService {

    @Resource
    private CompanyRepository repository;

    @Override
    public EntityRepository<Company, Integer> getRepository() {
        return repository;//提供对应的repository
    }

    //...其他功能实现
}
