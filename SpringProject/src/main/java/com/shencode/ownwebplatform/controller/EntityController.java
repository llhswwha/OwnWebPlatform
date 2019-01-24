package com.shencode.ownwebplatform.controller;


import com.shencode.ownwebplatform.entity.BaseEntity;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.util.TUtil;
import org.apache.commons.lang3.ClassUtils;
import org.codehaus.jackson.map.util.ClassUtil;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public abstract class EntityController<T extends BaseEntity,ID> {

    private Class <T> entityClass;

    public EntityController(){
        //从泛型T，获取到Class<T>，后续需要用到
        entityClass= TUtil.getClassT(this);
    }


    public abstract EntityService<T, ID> getService();//抽象方法，强迫子类必须重写，提供自己的实现

    //返回模板给前端
    @GetMapping("template")
    public T getTemplate() throws IllegalAccessException, InstantiationException {
        T entity=entityClass.newInstance();
        return entity;
    }

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

    @DeleteMapping("{id}") //因为有DeleteMapping了 "{id}"改成 {id}
    public Message<T> delete(@PathVariable("id") ID id) {
        return getService().deleteById(id);
    }

    @GetMapping("list")
    public List<T> getAll() {
        return getService().getAll();
    }

    @GetMapping("{id}")
    public Message<T> get(@PathVariable("id") ID id) {
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

    @GetMapping("count")
    public Message<Long> getCount() {
        return getService().getCount();
    }

  /*  @GetMapping("users")
    public Page<User> findByParam(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable) {
        return userQueryDSLRepository.findAll(predicate,pageable);
    }*/
  //另一种条件查询方式 继续研究
}

