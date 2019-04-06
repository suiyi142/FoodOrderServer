package com.fos.mobile.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fos.mobile.service.UserService;
import com.fos.mobile.service.iservice.IserviceCallBack;
import com.fos.mobile.servlet.iservlet.IpeopleServlet;
import com.fos.mobile.util.MyMessage;

import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet implements IpeopleServlet {

	private static final String TAG = "UserServlet:";

	private UserService userService = new UserService();

	/*
	 * 客户登录
	 * 
	 * @see
	 * com.fos.mobile.servlet.Ipeople#login(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		final OutputStream out = response.getOutputStream();
		userService.login(account, password, new IserviceCallBack() {

			@Override
			public void onSuccess(MyMessage message) throws IOException {
				out.write(message.msg.getBytes("utf-8"));

			}

			@Override
			public void onFailure(MyMessage message) throws IOException {
				out.write(String.valueOf(message.a).getBytes("utf-8"));

			}
		});
		out.flush();
		out.close();
	}

	/*
	 * 用户注册
	 * 
	 * @see
	 * com.fos.mobile.servlet.Ipeople#login(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		final OutputStream out = response.getOutputStream();
		userService.register(account, name, password, new IserviceCallBack() {

			// 登录成功返回1
			@Override
			public void onSuccess(MyMessage message) throws IOException {
				out.write(String.valueOf(message.a).getBytes("utf-8"));

			}

			// 登录失败返回失败原因
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
	 * 获取商家列表
	 * 
	 * @see com.fos.mobile.servlet.Ipeople#findBusiness(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void findBusiness(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String jsonString = userService.findBusiness();
		OutputStream out = response.getOutputStream();
		out.write(jsonString.getBytes("utf-8"));
		out.flush();
		out.close();

	}

	/*
	 * 获取商品列表
	 * 
	 * @see com.fos.mobile.servlet.Ipeople#findgoods(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void findGoods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String b_id = request.getParameter("b_id");
		String jsonString = userService.findgoods(b_id);
		OutputStream out = response.getOutputStream();
		out.write(jsonString.getBytes("utf-8"));
		out.flush();
		out.close();
	}

}
