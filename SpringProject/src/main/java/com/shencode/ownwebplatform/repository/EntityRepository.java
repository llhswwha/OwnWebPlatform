package com.shencode.ownwebplatform.repository;

import com.shencode.ownwebplatform.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface EntityRepository<T,ID> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {

       List<T> findAllByActive(Boolean active);

        Optional<T> findByIdAndActive(Integer id,boolean active);

}
