package com.fos.mobile.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

import com.fos.mobile.bean.Goods;

public class GoodsDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 查询所有商品
	 * 
	 * @param b_id
	 * @return
	 */
	public List<Goods> findAllGoods(String b_id) {
		List<Goods> list = null;
		String sql = "select * from t_goods where b_id = ?";
		try {
			Connection con = JdbcUtils.getConnection();
			list = qr.query(con, sql, new BeanListHandler<Goods>(Goods.class),
					b_id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
