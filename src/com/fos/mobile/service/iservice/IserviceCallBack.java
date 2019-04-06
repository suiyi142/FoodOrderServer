package com.fos.mobile.service.iservice;

import java.io.IOException;

import com.fos.mobile.util.MyMessage;


public interface IserviceCallBack {
	/*
	 * 请求失败
	 */
	public void onFailure(MyMessage message) throws IOException;

	/*
	 * 请求成功
	 */
	public void onSuccess(MyMessage message) throws IOException;

}
