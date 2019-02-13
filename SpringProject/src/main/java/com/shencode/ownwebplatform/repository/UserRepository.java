package com.shencode.ownwebplatform.repository;

import com.shencode.ownwebplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository   extends  EntityRepository<User,Integer> {

   public   User findByLoginNameAndActive(String loginName,boolean active);

   public List<User> findAll();

   @Query(" select u from  User u  where u.name like %:n% and u.active=true ")
   public Page<User> getUserbyName(@Param("n") String name, Pageable pageable);


}
