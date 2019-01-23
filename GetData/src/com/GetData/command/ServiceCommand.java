package com.GetData.command;

import java.util.List;

public interface ServiceCommand<T> {

	//��ѯ���е�����
		 public List<T>  queryAllData();
		 
		//��������
		 public boolean insertData(T t);
		 
		 //�޸�����
		 public boolean updateData(T t);
		 
		 //ɾ������
		 public boolean deleteData(String code);
		 
		 //��ѯһ������ͨ��ID
		 public T quertDataByid(String code);
}
