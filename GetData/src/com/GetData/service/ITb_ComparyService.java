package com.GetData.service;

import java.util.List;

import com.GetData.command.ServiceCommand;
import com.GetData.domain.Tb_Compary;

public interface ITb_ComparyService extends ServiceCommand<Tb_Compary>{
	 
	    List<Tb_Compary> queryAllData();
		
	    boolean deleteData(String id);

		boolean insertData(Tb_Compary t);

		boolean updateData(Tb_Compary t);
		
		Tb_Compary  quertDataByid(String id);

}
