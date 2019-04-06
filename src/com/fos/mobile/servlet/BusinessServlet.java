package com.fos.mobile.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.fos.mobile.bean.Business;
import com.fos.mobile.service.BusinessService;
import com.fos.mobile.service.iservice.IserviceCallBack;
import com.fos.mobile.servlet.iservlet.IpeopleServlet;
import com.fos.mobile.util.Md5Util;
import com.fos.mobile.util.MyMessage;

public class BusinessServlet extends BaseServlet implements IpeopleServlet {

	private static final String TAG = "BusinessServlet:";

	private BusinessService businessService = new BusinessService();

	/*
	 * �̼ҵ�¼
	 * 
	 * @see
	 * com.fos.mobile.servlet.Ipeople#login(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String b_id = request.getParameter("b_id");
		String password = request.getParameter("password");
		final OutputStream out = response.getOutputStream();
		businessService.login(b_id, password, new IserviceCallBack() {
			// ��¼�ɹ������̼���Ϣ
			@Override
			public void onSuccess(MyMessage message) throws IOException {
				out.write(message.msg.getBytes("utf-8"));

			}

			// ��¼ʧ�ܷ���0
			@Override
			public void onFailure(MyMessage message) throws IOException {
				out.write(String.valueOf(message.a).getBytes("utf-8"));

			}
		});
		out.flush();
		out.close();

	}

	/*
	 * �̼�ע��
	 * 
	 * @see
	 * com.fos.mobile.servlet.Ipeople#login(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		int max_seats = Integer.parseInt(request.getParameter("max_seats"));
		int current_seats = max_seats;
		String other = request.getParameter("other");
		final OutputStream out = response.getOutputStream();
		businessService.register(address, password, current_seats, max_seats,
				other, new IserviceCallBack() {

					@Override
					public void onSuccess(MyMessage message) throws IOException {
						out.write(String.valueOf(message.a).getBytes("utf-8"));
					}

					@Override
					public void onFailure(MyMessage message) throws IOException {
						System.out.println(TAG + message.msg);
						out.write(message.msg.getBytes("utf-8"));

					}
				});
		out.flush();
		out.close();

	}

	/*
	 * ��ȡ�̼��б�
	 * 
	 * @see com.fos.mobile.servlet.Ipeople#findBusiness(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void findBusiness(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String jsonString = businessService.findBusiness();
		OutputStream out = response.getOutputStream();
		out.write(jsonString.getBytes("utf-8"));
		out.flush();
		out.close();

	}

	/*
	 * ��ȡ��Ʒ�б�
	 * 
	 * @see com.fos.mobile.servlet.Ipeople#findgoods(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void findGoods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String b_id = request.getParameter("b_id");
		String jsonString = businessService.findgoods(b_id);
		OutputStream out = response.getOutputStream();
		out.write(jsonString.getBytes("utf-8"));
		out.flush();
		out.close();

	}

}
