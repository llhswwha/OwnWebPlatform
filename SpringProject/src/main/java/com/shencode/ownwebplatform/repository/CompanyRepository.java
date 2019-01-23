package com.shencode.ownwebplatform.repository;

import com.shencode.ownwebplatform.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends EntityRepository<Company,Integer> {
    //...其他数据库方法
}
