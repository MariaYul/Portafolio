package com.telcel.gsrh.solicitudcurso.conexion;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.telcel.gsrh.solicitudcurso.exception.DataBaseException;



public class Conexion {
	private static Connection cnx = null;
	

	public static Connection obtainConnectionBaseG() throws DataBaseException {
		String url = new String();
		String driver = new String();

		url = "jdbc:oracle:thin:@10.191.241.52:1524:basg";
		driver = "oracle.jdbc.driver.OracleDriver";
		try {
			try {
				DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());
			} catch (Exception e) {
				throw new DataBaseException("No se pudo registrar el driver: " + e.getMessage());
			}
			cnx = DriverManager.getConnection(url, "SVBCS01", "soporte01#");
		} catch (Exception e) {
			throw new DataBaseException("Ocurrió un error al tratar de obtener la conexión: " + e.getMessage());
		}
		return cnx;
	}


	public static void closeRS(ResultSet ars_rs) throws DataBaseException {
		try {
			if (ars_rs != null)
				ars_rs.close();
		} catch (Exception e) {
			throw new DataBaseException("Error al cerrar el ResultSet: " + e.getMessage());
		}
	}

	public static void closeST(Statement ast_st) throws DataBaseException {
		try {
			if (ast_st != null)
				ast_st.close();
		} catch (Exception e) {
			throw new DataBaseException("Error al cerrar el Statement: " + e.getMessage());
		}
	}

	public static void commit(Connection ac_conn) throws DataBaseException {
		try {
			if (ac_conn != null)
				ac_conn.commit();
		} catch (Exception e) {
			throw new DataBaseException("Error en el commit: " + e.getMessage());
		}
	}

	public static void rollBack(Connection ac_conn) throws DataBaseException {
		try {
			if (ac_conn != null)
				ac_conn.rollback();
		} catch (Exception e) {
			throw new DataBaseException("Error en el rollback: " + e.getMessage());
		}
	}
}
