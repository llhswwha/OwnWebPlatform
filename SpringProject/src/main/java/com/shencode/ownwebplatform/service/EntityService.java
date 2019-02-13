package com.shencode.ownwebplatform.service;

import com.shencode.ownwebplatform.entity.BaseEntity;
import com.shencode.ownwebplatform.model.ListParam;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.repository.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EntityService<T extends BaseEntity,ID> {
    public EntityRepository<T, ID> getRepository();

    public Message<T> add(T entity);

    //更新，是更新全部属性
    public Message<T> update(T entity);

    public Message<T> deleteById(ID id);

    public Message<T> delete(T entity);

    //获取全部
    public List<T> getAll();

    //获取单个
    public Message<T> get(ID id);

    //添加列表
    public Message<List<T>> addList(ListParam<T> list);

    //获取分页
    public Page<T> getPage(Pageable pageable);

    //删除全部，一般不开放到Controller
    public List<T> deleteAll();

    //更新，更新部分属性，以patch方式区分
    public Message<T> update(Map<String, Object> map);

    //条件查询列表
    public Message<List<T>> queryAll(ConditionModel condition);

    //条件查询分页
    public Message<Page<T>> queryPage(ConditionModel condition);

    //获取数量
    public Message<Long> getCount();

    //初始化数据
    public Boolean initData();
}
