package com.fos.mobile.servlet.iservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IpeopleServlet {

	/**
	 * ��¼
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
	 * ע��
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
	 * ��ѯ�̼��б�
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
	 * ��ѯ��Ʒ
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
