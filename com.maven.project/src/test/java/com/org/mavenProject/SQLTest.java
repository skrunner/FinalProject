package com.org.mavenProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class SQLTest {

	@Test
	public void sqlTest() throws ClassNotFoundException, SQLException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1443;databaseName=test; intergratedSecurity=true", "", "");
		System.out.println("connected");

		Statement st = conn.createStatement();
		String querry = "Select * From employee";
		ResultSet rs = st.executeQuery(querry);

		while (rs.next()) {
			System.out.println(rs.getString("name"));
		}
	}

}
