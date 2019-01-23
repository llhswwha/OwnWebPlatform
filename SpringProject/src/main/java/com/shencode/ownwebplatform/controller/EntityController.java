package com.shencode.ownwebplatform.controller;


import com.shencode.ownwebplatform.entity.BaseEntity;
import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.service.EntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public abstract class EntityController<T extends BaseEntity<ID>,ID> {

    public abstract EntityService<T, ID> getService();//抽象方法，强迫子类必须重写，提供自己的实现

    @PostMapping("add")
    public Message<T> add(T entity) {
        return getService().add(entity);
    }

    @PostMapping("update")
    public Message<T> update(T entity) {
        return getService().update(entity);
    }

    @PutMapping("put")
    public Message<T> put(T entity) {
        return getService().update(entity);
    }

    //更新部分属性的功能
    @PatchMapping("patch")
    public Message<T> patch(@RequestParam Map<String, Object> map) {
        return getService().update(map);
    }

    @DeleteMapping("delete")
    public Message<T> delete(ID id) {
        return getService().delete(id);
    }

    @GetMapping("")
    public List<T> getAll() {
        return getService().getAll();
    }

    @GetMapping("{id}")
    public T get(@PathVariable("id") ID id) {
        return getService().get(id);
    }

    @GetMapping("page")
    public Page<T> getPage(@PageableDefault(value = 15, sort = {"Name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return getService().getPage(pageable);
    }

    @PostMapping("queryAll")
    public List<T> queryList(ConditionModel condition) {
        System.out.println("queryList");
        System.out.println(condition);
        return getService().queryAll(condition);
    }

    @PostMapping("queryPage")
    public Page<T> queryPage(ConditionModel condition) {
        System.out.println("queryList");
        System.out.println(condition);
        return getService().queryPage(condition);
    }

  /*  @GetMapping("users")
    public Page<User> findByParam(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable) {
        return userQueryDSLRepository.findAll(predicate,pageable);
    }*/
  //另一种条件查询方式 继续研究
}

