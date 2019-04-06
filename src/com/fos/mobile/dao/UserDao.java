package com.fos.mobile.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.fos.mobile.bean.User;
import com.fos.mobile.util.Md5Util;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 通过id和md5加密的密码查询用户是否存在
	 * 
	 * @param b_id
	 * @param md5Password
	 * @return 登录是否成功
	 */
	public User findUser(String account, String md5Password) {
		String sql = "select account,name from t_user where account=? and password = ?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), account,
					md5Password);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过id查询用户是否存在
	 * 
	 * @param b_id
	 * @param md5Password
	 * @return 登录是否成功
	 */
	public boolean findUser(String account) {
		String sql = "select account,name from t_user where account=?";
		boolean b = false;
		try {
			User user = qr.query(sql, new BeanHandler<User>(User.class),
					account);
			if (user != null) {
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
	 * @param user
	 */
	public int addUser(User user) {
		String sql = "insert into t_user(account,name,password) values(?,?,?)";
		Object[] params = { user.getAccount(), user.getName(),
				Md5Util.md5(user.getPassword()) };
		int rows = 0;
		try {
			rows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
