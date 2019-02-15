package com.shencode.ownwebplatform.service.impl;

import com.shencode.ownwebplatform.entity.DmtGroupinfo;
import com.shencode.ownwebplatform.entity.DmtNodeinfo;
import com.shencode.ownwebplatform.model.Message;
import com.shencode.ownwebplatform.repository.DmtGroupinfoRepository;
import com.shencode.ownwebplatform.repository.DmtNodeinfoRepository;
import com.shencode.ownwebplatform.repository.EntityRepository;
import com.shencode.ownwebplatform.service.DmtGroupinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DmtGroupinfoRepositoryImpl extends EntityServiceImpl<DmtGroupinfo>  implements DmtGroupinfoService {
    @Resource
    private DmtGroupinfoRepository dmtGroupinfoRepository;

    @Resource
    private DmtNodeinfoRepository dmtNodeinfoRepository;

    @Override
    public EntityRepository<DmtGroupinfo, Integer> getRepository() {
        return dmtGroupinfoRepository;
    }

    //添加设备组节点
    @Override
    public Message<DmtGroupinfo> add(DmtGroupinfo entityNew) {
        try
        {
            SetDmtNodeinfo(entityNew);
            return super.add(entityNew);
        }
        catch (Exception ex)
        {
            return  new Message<>(1,ex.toString());
        }

    }

    //修改设备组节点
    @Override
    public Message<DmtGroupinfo> update(DmtGroupinfo entity) {
        try
        {
            SetDmtNodeinfo(entity);
            return super.update(entity);
        }
        catch (Exception ex)
        {
            return  new Message<>(1,ex.toString());
        }

    }

    //删除设备组节点
    @Override
    public Message<DmtGroupinfo> delete(DmtGroupinfo entity) {
        try
        {
            SetDmtNodeinfo(entity);
            return super.delete(entity);
        }
        catch (Exception ex)
        {
            return new Message<>(1,ex.toString());
        }

    }

    //操作设备组节点时关联监控节点
    private void  SetDmtNodeinfo(DmtGroupinfo dmtGroupinfo)
    {
        List<Integer> integerList=dmtGroupinfo.getNodeInfoidList();
        if(integerList.size()==0) return;
        Set<DmtNodeinfo> dmtNodeinfoSet=new HashSet<>();
        for(int i=0;i<integerList.size();i++)
        {
            DmtNodeinfo dmtNodeinfo=dmtNodeinfoRepository.findByIdAndActive(integerList.get(i),true).get();
            dmtNodeinfoSet.add(dmtNodeinfo);
        }
        dmtGroupinfo.setDmtNodeinfoSet(dmtNodeinfoSet);
    }
}
