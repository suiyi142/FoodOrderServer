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
	 * ͨ��id��md5���ܵ������ѯ�û��Ƿ����
	 * 
	 * @param b_id
	 * @param md5Password
	 * @return ��¼�Ƿ�ɹ�
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
	 * ͨ��id��ѯ�û��Ƿ����
	 * 
	 * @param b_id
	 * @param md5Password
	 * @return ��¼�Ƿ�ɹ�
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
	 * ����û�
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
