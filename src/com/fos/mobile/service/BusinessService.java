package com.fos.mobile.service;

import java.io.IOException;
import java.util.List;

import com.fos.mobile.bean.Business;
import com.fos.mobile.bean.Goods;
import com.fos.mobile.bean.User;
import com.fos.mobile.dao.BusinessDao;
import com.fos.mobile.dao.GoodsDao;
import com.fos.mobile.service.iservice.IserviceCallBack;
import com.fos.mobile.util.Md5Util;
import com.fos.mobile.util.MyMessage;
import com.fos.mobile.util.UUidUtils;
import com.google.gson.Gson;

public class BusinessService {
	private static final String TAG = "BusinessService:";
	BusinessDao businessDao = new BusinessDao();
	GoodsDao goodsDao = new GoodsDao();

	/*
	 * 查询所有商家
	 */
	public String findBusiness() {
		List<Business> list = businessDao.findBusiness();

		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		System.out.println(TAG + gsonString);
		return gsonString;
	}

	/*
	 * 商家登录
	 */
	public void login(String b_id, String password,
			IserviceCallBack iserviceCallBack) throws IOException {
		String md5Password = Md5Util.md5(password);
		Business business = businessDao.findBusiness(b_id, md5Password);
		MyMessage message = new MyMessage();
		if (business != null) {
			message.msg = new Gson().toJson(business);
			iserviceCallBack.onSuccess(message);
		} else {
			message.a = 0;
			iserviceCallBack.onFailure(message);
		}
		System.out.println(TAG + b_id + " login");

	}

	/*
	 * 商家注册
	 */
	public void register(String address, String password, int current_seats,
			int max_seats, String other, IserviceCallBack iserviceCallBack) throws IOException {
		boolean b = businessDao.findBusiness(address);
		MyMessage message = new MyMessage();
		if (b) {
			message.msg = "这个地址已存在";
			iserviceCallBack.onFailure(message);
		} else {
			int row = businessDao.addBusiness(new Business(UUidUtils.uuid(), address, password, current_seats, max_seats, other));
			if (row == 0) {
				message.msg = "数据库错误";
				iserviceCallBack.onFailure(message);
			} else {
				message.a = 1;
				iserviceCallBack.onSuccess(message);
			}
		}
	}

	/*
	 * 查询商品列表
	 */
	public String findgoods(String b_id) {
		List<Goods> list = goodsDao.findAllGoods(b_id);
		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		System.out.println(TAG + gsonString);
		return gsonString;
	}

}















