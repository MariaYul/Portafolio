package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Serializador {
    public void deserializar(ObjectInputStream in, Object clase) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        
        Integer clave = in.readInt();
        String nombre = (String)in.readObject();
        
        if(clase instanceof Area) {
            Area areaInstancia = (Area) clase;
            areaInstancia.setClave(clave);
            areaInstancia.setNombre(nombre);
        }  else if(clase instanceof Documento) {
            Documento documentoInstancia = (Documento) clase;
            documentoInstancia.setClave(clave);
            documentoInstancia.setNombre(nombre);
        }  else if(clase instanceof Solicitud) {
        	Solicitud solicitudInstancia = (Solicitud) clase;
        	solicitudInstancia.setClave(clave);
        	solicitudInstancia.setNombre(nombre);
        } else if(clase instanceof Fase) {
            Fase faseInstancia = (Fase) clase;
            faseInstancia.setClave(clave);
            faseInstancia.setNombre(nombre);
        } else if(clase instanceof Modulo) {
            Modulo moduloInstancia = (Modulo) clase;
            moduloInstancia.setClave(clave);
            moduloInstancia.setNombre(nombre);
        } else if(clase instanceof Region) {
            Region regionInstancia = (Region) clase;
            regionInstancia.setClave(clave);
            regionInstancia.setNombre(nombre);
        } else if(clase instanceof Rol) {
            Rol rolInstancia = (Rol) clase;
            rolInstancia.setClave(clave);
            rolInstancia.setNombre(nombre);
        } else if(clase instanceof Usuario) {
            Apellidos apellidos = (Apellidos) in.readObject();
            Integer activo = in.readInt();
            String contrasennia = in.readUTF();
            Integer bloqueo = in.readInt();
            Date fechaExpiraContrasennia = (Date) in.readObject();
            Area area = (Area) in.readObject();
            Region region = (Region) in.readObject();
            
            Usuario usuarioInstancia = (Usuario) clase;
            usuarioInstancia.setNumero(clave);
            usuarioInstancia.setNombre(nombre);
            usuarioInstancia.setApellidos(apellidos);
            usuarioInstancia.setActivo(activo);
            usuarioInstancia.setContrasennia(contrasennia);
            usuarioInstancia.setBloqueo(bloqueo);
            usuarioInstancia.setFechaExpiraContrasennia(fechaExpiraContrasennia);
            usuarioInstancia.setArea(area);
            usuarioInstancia.setRegion(region);
        } 
    }
    
    public void serializar(ObjectOutputStream out, Object clase) throws IOException  {
        out.defaultWriteObject();
        
        if(clase instanceof Area) {
            Area areaInstancia = (Area)clase;
            
            out.writeInt(areaInstancia.getClave());
            out.writeObject(areaInstancia.getNombre());
        } else if(clase instanceof Documento) {
            Documento documentoInstancia = (Documento)clase;
            
            out.writeInt(documentoInstancia.getClave());
            out.writeObject(documentoInstancia.getNombre());
        } else if(clase instanceof Solicitud) {
        	Solicitud solicitudInstancia = (Solicitud)clase;
            
            out.writeInt(solicitudInstancia.getClave());
            out.writeObject(solicitudInstancia.getNombre());
        } else if(clase instanceof Fase) {
            Fase faseInstancia = (Fase)clase;
            
            out.writeInt(faseInstancia.getClave());
            out.writeObject(faseInstancia.getNombre());
        } else if(clase instanceof Modulo) {
            Modulo moduloInstancia = (Modulo)clase;
            
            out.writeInt(moduloInstancia.getClave());
            out.writeObject(moduloInstancia.getNombre());
        } else if(clase instanceof Region) {
            Region regionInstancia = (Region)clase;
            
            out.writeInt(regionInstancia.getClave());
            out.writeObject(regionInstancia.getNombre());
        } else if(clase instanceof Rol) {
            Rol rolInstancia = (Rol)clase;
            
            out.writeInt(rolInstancia.getClave());
            out.writeObject(rolInstancia.getNombre());
        } else if(clase instanceof Usuario) {
            Usuario usuarioInstancia = (Usuario)clase;
            
            out.writeInt(usuarioInstancia.getNumero());
            out.writeObject(usuarioInstancia.getNombre());
            out.writeObject(usuarioInstancia.getApellidos());
            out.writeInt(usuarioInstancia.getActivo());
            out.writeChars(usuarioInstancia.getContrasennia());
            out.writeInt(usuarioInstancia.getBloqueo());
            out.writeObject(usuarioInstancia.getFechaExpiraContrasennia());
            out.writeObject(usuarioInstancia.getArea());
            out.writeObject(usuarioInstancia.getRegion());
        }
    }
}
