package com.fos.mobile.service.iservice;

import java.io.IOException;

import com.fos.mobile.util.MyMessage;


public interface IserviceCallBack {
	/*
	 * ����ʧ��
	 */
	public void onFailure(MyMessage message) throws IOException;

	/*
	 * ����ɹ�
	 */
	public void onSuccess(MyMessage message) throws IOException;

}
