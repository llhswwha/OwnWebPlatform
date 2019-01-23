package com.GetData.service;

import java.util.List;

import com.GetData.command.ServiceCommand;
import com.GetData.domain.Tb_Customer;

public interface ITb_CustomerService extends ServiceCommand<Tb_Customer> {
	 
    List<Tb_Customer> queryAllData();
	
    boolean deleteData(String code);

	boolean insertData(Tb_Customer t);

	boolean updateData(Tb_Customer t);
	
	Tb_Customer  quertDataByid(String code);
	
}
