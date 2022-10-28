package br.com.treino.festa.connectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory con = new ConnectionFactory();
		Connection conn = con.getConnection();
		String sql = "SELECT * FROM convidado";
		Statement stm = conn.createStatement();
		System.out.println(stm.execute(sql));
	}
}
