package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.BaseEntity;
import com.shencode.ownwebplatform.entity.Message;
import com.shencode.ownwebplatform.module.condition.ConditionQuery;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.module.condition.ui.OrderBy;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.EntityService;
import com.shencode.ownwebplatform.util.MapUtil;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public abstract class EntityServiceImpl<T extends BaseEntity<ID>,ID> implements EntityService<T,ID> {

    private Class <T> entityClass;

    public EntityServiceImpl(){
        //从泛型T，获取到Class<T>，后续需要用到
        initEntityClass();
    }

    private void initEntityClass(){
        entityClass=null;
        Type t=getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p=((ParameterizedType)t).getActualTypeArguments();
            entityClass=(Class <T>)p[0];
        }
    }

    @Override
    public abstract EntityRepository<T, ID> getRepository();//抽象方法，强迫子类必须重写，提供自己的实现

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }
    //单挑添加
    @Override
    public Message<T> add(T entityNew) {
        System.out.println("add");
        System.out.println(entityNew);
        Message<T> message = null;
        try {
            ID id=entityNew.getId();
            if(id==null){//一般路径
                T result = getRepository().save(entityNew);
                message = new Message(0, "成功");
                message.setData(result);
            }
            else{//用户添加路径
                T entity = get(id);
                if (entity != null) {
                    message = new Message(1, "已存在！");//用户不能重复添加，并且用户名相同时，save就会变成修改原来的用户属性了。
                    message.setData(entity);
                } else {
                    T result = getRepository().save(entityNew);
                    message = new Message(0, "成功");
                    message.setData(result);
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            message = new Message(1, e.toString());
        }
        return message;
    }
    //多条添加(待验证)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public  Message<List<T>>  adds(List<T> tList)
    {
        Message<List<T>> message =new Message(0,"成功！");
            for (int i=0;i<=tList.size();i++)
            {
                Message  message1=null;
                try {
                    T t = tList.get(i);
                    T result = getRepository().save(t);
                    message1.setState(0); message1.setDescribe("成功！");
                }
                catch (Exception e)
                {
                    message.setState(1);
                    message.setDescribe(message.getDescribe()+","+i+","+e.toString());
                }

            }
        return  message;
    }
    //修改
    @Override
    public Message<T> update(T entity) {
        Message<T> message =null;
        try {
            T entityOld=get(entity.getId());
            if (entityOld != null) {
                getRepository().save(entity);
                message = new Message(0, "成功");
                message.setData(entity);
            } else {
                message = new Message(1, "未找到该条数据! id="+entity.getId());
            }
        } catch (Exception e) {
            message = new Message(1, e.toString());
        }
        return message;
    }

    @Override
    public Message<T> update(Map<String,Object> map){
        Message<T> msg=null;
        try{
            Object id=map.get("id");
            T entity=get((ID)id);
            if(entity==null){
            }else{
                MapUtil.mapToObject(map,entity);//
            }
            msg=new Message(0,"成功");
            msg.setData(entity);
        }catch (Exception ex){
            msg=new Message(1,"失败");
        }

        return msg;
    }

    @Override
    public Message<T> deleteById(ID id) {
        T entity=get(id);
        Message<T> message =null;
        if(entity!=null){
            try {
                getRepository().delete(entity);
                message = new Message(0, "成功");
                message.setData(entity);
            } catch (Exception e) {
                message = new Message(1, e.toString());
            }
        }
        else{
            message = new Message(1, "未找到该条数据! id="+id);
        }
        return message;
    }
    //根据类删除（存在关联关系的多表删除）
    @Override
    public Message<T>  delete(T entity)
    {
        Message<T> message=null;
        try {
            T entityOld=get(entity.getId());
            if (entityOld != null) {
                getRepository().delete(entity);
                message = new Message(0, "成功");
                message.setData(entity);
            } else {
                message = new Message(1, "未找到该条数据! id="+entity.getId());
            }
        } catch (Exception e) {
            message = new Message(1, e.toString());
        }
        return  message;
    }


    @Override
    public List<T> deleteAll() {
        List<T> all=getAll();
        getRepository().deleteAllInBatch();
        return all;
    }

    @Override
    public T get(ID id) {
        Optional<T> op = getRepository().findById(id);
        if(op.isPresent()){
            return op.get();
        }
        return null;
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Resource
    private EntityManager entityManager;//必须有

    @Override
    public List<T> queryAll(ConditionModel condition){
        ConditionQuery queryUtil=new ConditionQuery(entityManager,entityClass,condition);
        return queryUtil.findList();
    }

    @Override
    public Page<T> queryPage(ConditionModel condition){
        ConditionQuery queryUtil=new ConditionQuery(entityManager,entityClass,condition);
        Pageable pageable=getPageable(condition);
        Page<T> page=getRepository().findAll((itemRoot,query,criteriaBuilder)->{  //需要JpaSpecificationExecutor接口
            Predicate result=queryUtil.getPredicate(itemRoot,query,criteriaBuilder);
            return result;
        },pageable);
        return page;
    }

    private Pageable getPageable(ConditionModel condition){
        List<Sort.Order> orders=new ArrayList<>();
        for (OrderBy orderBy : condition.getOrderBys()) {
            if (orderBy.isAsc()) {
                orders.add(Sort.Order.asc(orderBy.getColumn()));
            } else {
                orders.add(Sort.Order.desc(orderBy.getColumn()));
            }
        }
        Pageable pageable =PageRequest.of (condition.getPage(),condition.getSize(),Sort.by(orders));
        return pageable;
    }
}
