package com.shencode.ownwebplatform.service;

import com.shencode.ownwebplatform.entity.Role;
import org.springframework.stereotype.Service;
import java.util.List;

public interface RoleService extends  EntityService<Role,Integer> {

    public List<Role>  findAll();

}
