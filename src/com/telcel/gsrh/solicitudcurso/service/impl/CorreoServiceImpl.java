package com.telcel.gsrh.solicitudcurso.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.telcel.gsrh.solicitudcurso.dao.CorreoDao;
import com.telcel.gsrh.solicitudcurso.exception.GeneralException;
import com.telcel.gsrh.solicitudcurso.service.CorreoService;

public class CorreoServiceImpl implements CorreoService{

	@Override
	public String getMail(Integer numEmp) {
		CorreoDao dao = new CorreoDao();
		String email = null;
		try {
			email = dao.getEmail(numEmp);
			System.out.println("email=" + email);
			if(!emailIsValid(email)) {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
	        throw new GeneralException((new StringBuilder(String.valueOf(e.getMessage()))).append(" - ERROR AL CONSULTAR EMAIL DEL EMPLEADO " + numEmp).toString());
		}
		return email;
	}
	
	public static boolean emailIsValid(String email) {
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email.trim());
		if (mather.find()) {
			return true;
		}
		return false;
	}

}
