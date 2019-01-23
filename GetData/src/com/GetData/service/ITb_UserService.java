package com.GetData.service;

import com.GetData.command.ServiceCommand;
import com.GetData.domain.Tb_User;
import java.util.List;

public interface ITb_UserService extends ServiceCommand<Tb_User>{
	
	List<Tb_User> queryAllData();
	
	boolean deleteData(String code);

	boolean insertData(Tb_User t);

	boolean updateData(Tb_User t);
	
	Tb_User  quertDataByid(String loginName);
}
