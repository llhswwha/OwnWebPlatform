package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Menu;
import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.MenuRepository;
import com.shencode.ownwebplatform.service.MenuService;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl extends EntityServiceImpl<Menu> implements MenuService {

    @Resource
    private MenuRepository repository;

    @Override
    public EntityRepository<Menu, Integer> getRepository() {
        return repository;
    }


}
