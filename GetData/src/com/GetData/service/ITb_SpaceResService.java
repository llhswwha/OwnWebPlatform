package com.GetData.service;

import java.util.List;

import com.GetData.command.ServiceCommand;
import com.GetData.domain.Tb_SpaceRes;

public interface ITb_SpaceResService extends ServiceCommand<Tb_SpaceRes>{
	
	List<Tb_SpaceRes> queryAllData();
	
    boolean deleteData(String id);

	boolean insertData(Tb_SpaceRes t);

	boolean updateData(Tb_SpaceRes t);
	
	Tb_SpaceRes  quertDataByid(String id);

}
