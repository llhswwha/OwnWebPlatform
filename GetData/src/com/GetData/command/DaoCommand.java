package com.GetData.command;

import java.util.List;

public interface DaoCommand<T> {
	//查询所有的数据
	 public List<T>  queryAllData();
	 
	//新增数据
	 public int insertData(T t);
	 
	 //修改数据
	 public int updateData(T t);
	 
	 //删除数据
	 public int deleteData(String code);
	 
	 //查询一条数据通过ID
	 public T quertDataByid(String code);
}
