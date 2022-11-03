package com.modu.persistence;

import javax.sql.DataSource;
import java.sql.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {
	@Autowired
	private DataSource dataSource; //object injection 
	
	@Test
	public void testConnection() {
		log.info("#dataSource: " + dataSource);
		try {
			Connection con = dataSource.getConnection();
			log.info("#con: " + con);
		}catch(SQLException se) {
			log.info("#testConnection() se: " + se);
		}
	}
}
