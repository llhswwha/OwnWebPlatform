package com.shencode.ownwebplatform.repository;

import com.shencode.ownwebplatform.entity.SpaceRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface SpaceResRepository extends EntityRepository<SpaceRes,Integer> {

}
