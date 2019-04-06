package com.fos.mobile.servlet.iservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IpeopleServlet {

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException;

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException;

	/**
	 * 查询商家列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void findBusiness(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException;

	/**
	 * 查询商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void findGoods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException;
}
