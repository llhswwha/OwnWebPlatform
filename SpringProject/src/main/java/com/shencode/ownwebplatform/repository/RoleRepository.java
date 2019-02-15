package com.shencode.ownwebplatform.repository;

import com.shencode.ownwebplatform.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends  EntityRepository<Role,Integer>
{
    public List<Role> findAll();


}
