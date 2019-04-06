package com.fos.mobile.service;

import java.io.IOException;
import java.util.List;

import com.fos.mobile.bean.Business;
import com.fos.mobile.bean.Goods;
import com.fos.mobile.bean.User;
import com.fos.mobile.dao.BusinessDao;
import com.fos.mobile.dao.GoodsDao;
import com.fos.mobile.dao.UserDao;
import com.fos.mobile.service.iservice.IserviceCallBack;
import com.fos.mobile.util.Md5Util;
import com.fos.mobile.util.MyMessage;
import com.google.gson.Gson;

public class UserService {
	private static final String TAG = "UserService:";

	private UserDao userDao = new UserDao();
	BusinessDao businessDao = new BusinessDao();
	GoodsDao goodsDao = new GoodsDao();

	/*
	 * �û���¼
	 */
	public void login(String account, String password,
			IserviceCallBack iserviceCallBack) throws IOException {
		String md5Password = Md5Util.md5(password);
		System.out.println(md5Password);
		User user = userDao.findUser(account, md5Password);
		System.out.println(user);
		MyMessage message = new MyMessage();
		if (user != null) {
			message.msg = new Gson().toJson(user);
			iserviceCallBack.onSuccess(message);
		} else {
			message.a = 0;
			iserviceCallBack.onFailure(message);
		}
		System.out.println(TAG + account + " login");
	}

	/*
	 * �û�ע��
	 */
	public void register(String account, String name, String password,
			IserviceCallBack iserviceCallBack) throws IOException {
		boolean b = userDao.findUser(account);
		MyMessage message = new MyMessage();
		if (b) {
			message.msg = "�û��Ѵ���";
			iserviceCallBack.onFailure(message);
		} else {
			int row = userDao.addUser(new User(account, name, password));
			if (row == 0) {
				message.msg = "���ݿ����";
				iserviceCallBack.onFailure(message);
			} else {
				message.a = 1;
				iserviceCallBack.onSuccess(message);
			}
		}

	}

	/*
	 * ��ѯ�����̼�
	 */
	public String findBusiness() {
		List<Business> list = businessDao.findBusiness();

		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		System.out.println(TAG + gsonString);
		return gsonString;
	}

	/*
	 * ��ѯ��Ʒ�б�
	 */
	public String findgoods(String b_id) {
		List<Goods> list = goodsDao.findAllGoods(b_id);
		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		System.out.println(TAG + gsonString);
		return gsonString;
	}

}
