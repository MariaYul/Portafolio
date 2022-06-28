package com.telcel.gsrh.solicitudcurso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.telcel.gsrh.solicitudcurso.conexion.Conexion;
import com.telcel.gsrh.solicitudcurso.exception.DataBaseException;
import com.telcel.gsrh.solicitudcurso.exception.GeneralException;

public class CorreoDao {

	public String getEmail(Integer numEmp) {
	 String correo = null;
     Connection conn = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
    
     try
     {
         conn = Conexion.obtainConnectionBaseG();
         if(conn == null)
         {
             System.out.print("error");
             throw new DataBaseException("ERROR AL CONECTARSE A LA BASE DE DATOS.");
         }
         ps = conn.prepareStatement(" SELECT CORREO FROM BASEG_COMPARATIVA WHERE NUM_EMPLEADO = ? ");
         ps.setInt(1, numEmp);
		 rs = ps.executeQuery();

			if (rs.next()) {
				correo = rs.getString("CORREO");
			}
			
     }
     catch(SQLException exe)
     {
         exe.printStackTrace();
         throw new GeneralException((new StringBuilder(String.valueOf(exe.getMessage()))).append(" - ERROR AL REALIZAR LA CONSULTA.").toString());
     }
     catch(Exception ex)
     {
         ex.printStackTrace();
     } finally {
     Conexion.closeRS(rs);
     Conexion.closeST(ps);
     
     }
     return correo;
     
}
	
	
	
}
