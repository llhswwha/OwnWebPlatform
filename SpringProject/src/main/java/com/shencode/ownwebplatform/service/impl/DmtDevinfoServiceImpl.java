package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.DmtDevinfo;
import com.shencode.ownwebplatform.entity.DmtGroupinfo;
import com.shencode.ownwebplatform.entity.Type;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.repository.DmtDevinfoRepository;
import com.shencode.ownwebplatform.repository.DmtGroupinfoRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.repository.TypeRepository;
import com.shencode.ownwebplatform.service.DmtDevinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DmtDevinfoServiceImpl extends EntityServiceImpl<DmtDevinfo> implements DmtDevinfoService {
    @Resource
    private DmtDevinfoRepository dmtDevinfoRepository;

    @Resource
   private TypeRepository typeRepository;

    @Resource
    private DmtGroupinfoRepository dmtGroupinfoRepository;


    @Override
    public EntityRepository<DmtDevinfo, Integer> getRepository() {
        return dmtDevinfoRepository;
    }


    //添加设备
    @Override
    public Message<DmtDevinfo> add(DmtDevinfo entityNew) {
        try{
        SetType(entityNew);
        SetdmtGroupinfoSet(entityNew);
        return super.add(entityNew);
        }
        catch (Exception ex)
        {
            return  new  Message<>(1,ex.toString());
        }
    }

    //修改设备
    @Override
    public Message<DmtDevinfo> update(DmtDevinfo entity) {
        try{
            SetType(entity);
            SetdmtGroupinfoSet(entity);
            return super.update(entity);
        }
        catch (Exception ex)
        {
            return  new Message(1,ex.toString());
        }
    }

    //删除设备
    @Override
    public Message<DmtDevinfo> delete(DmtDevinfo entity) {
        try
        {
            SetType(entity);
            SetdmtGroupinfoSet(entity);
            return super.delete(entity);
        }
       catch (Exception ex)
       {
           return  new Message(1,ex.toString());
       }
    }

    private  void  SetType(DmtDevinfo dmtDevinfo)
    {
        Integer typeid=dmtDevinfo.getTypeid();
        if (typeid==null) return;
        Type type=typeRepository.findByIdAndActive(typeid,true).get();
        dmtDevinfo.setType(type);
    }

    //添加设备时关联监控组节点,(给某一个设备添加多个监控组的时候用)
    private  void SetdmtGroupinfoSet(DmtDevinfo dmtDevinfo)
    {
        List<Integer> integerList=dmtDevinfo.getDmtGroupIdList();
        if(integerList.size()==0) return;
        Set<DmtGroupinfo>  dmtGroupinfoSet= new HashSet<>();
        for (int i=0;i<integerList.size();i++)
        {
            DmtGroupinfo dmtGroupinfo=dmtGroupinfoRepository.findByIdAndActive(integerList.get(i),true).get();
            dmtGroupinfoSet.add(dmtGroupinfo);
        }
        dmtDevinfo.setDmtGroupinfoSet(dmtGroupinfoSet);
    }
}

