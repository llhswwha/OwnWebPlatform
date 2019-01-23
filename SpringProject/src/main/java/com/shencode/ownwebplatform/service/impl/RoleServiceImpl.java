package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.Role;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.RoleRepository;
import com.shencode.ownwebplatform.service.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends EntityServiceImpl<Role> implements RoleService {
     @Resource
     private RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {

        List<Role> list=new ArrayList<Role>();
        try{
            list=roleRepository.findAll();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return  null;
        }
        return list;
    }


    @Override
    public EntityRepository<Role, Integer> getRepository() {
        return roleRepository;
    }
}
