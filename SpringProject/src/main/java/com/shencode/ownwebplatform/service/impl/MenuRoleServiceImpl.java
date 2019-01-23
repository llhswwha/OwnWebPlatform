package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.MenuRole;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.MenuRoleRepository;
import com.shencode.ownwebplatform.service.MenuRoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuRoleServiceImpl extends EntityServiceImpl<MenuRole,Integer> implements MenuRoleService {
    @Resource
    private MenuRoleRepository menuRoleRepository;

    @Override
    public EntityRepository<MenuRole, Integer> getRepository() {
        return menuRoleRepository;
    }
}
