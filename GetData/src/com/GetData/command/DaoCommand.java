package com.GetData.command;

import java.util.List;

public interface DaoCommand<T> {
	//��ѯ���е�����
	 public List<T>  queryAllData();
	 
	//��������
	 public int insertData(T t);
	 
	 //�޸�����
	 public int updateData(T t);
	 
	 //ɾ������
	 public int deleteData(String code);
	 
	 //��ѯһ������ͨ��ID
	 public T quertDataByid(String code);
}
