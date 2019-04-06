package com.fos.mobile.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

import com.fos.mobile.bean.Business;
import com.fos.mobile.util.Md5Util;

public class BusinessDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 查询所有商家
	 * 
	 * @return商家列表（不包含密码）
	 */
	public List<Business> findBusiness() {
		List<Business> list = null;
		String sql = "select b_id ,address, current_seats, max_seats, other from t_business";
		try {
			Connection con = JdbcUtils.getConnection();
			list = qr.query(con, sql, new BeanListHandler<Business>(
					Business.class));

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 通过id和md5加密的密码查询商家是否存在
	 * 
	 * @param b_id
	 * @param md5Password
	 * @return 登录是否成功
	 */
	public Business findBusiness(String b_id, String md5Password) {
		String sql = "select b_id,address,current_seats,max_seats,other from t_business where b_id=? and password = ?";
		Business business = null;
		try {
			business = qr.query(sql, new BeanHandler<Business>(Business.class),
					b_id, md5Password);
			return business;
		} catch (SQLException e) {
			e.printStackTrace();
			return business;
		}

	}

	/**
	 * 通过address查询商家是否存在
	 * 
	 * @param b_id
	 * @param md5Password
	 * @return 商家是否存在
	 */
	public boolean findBusiness(String address) {
		String sql = "select address from t_business where address=?";
		boolean b = false;
		try {
			Business business = qr.query(sql, new BeanHandler<Business>(
					Business.class), address);
			if (business != null) {
				b = true;
			}
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			return b;
		}

	}

	/**
	 * 添加用户
	 * 
	 * @param business
	 * @return
	 */
	public int addBusiness(Business business) {
		String sql = "insert into t_business(b_id,address,password,current_seats,max_seats,other) values(?,?,?,?,?,?)";
		Object[] params = { business.getB_id(), business.getAddress(),
				Md5Util.md5(business.getPassword()),
				business.getCurrent_seats(), business.getMax_seats(),
				business.getOther() };
		int rows = 0;
		try {
			rows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
