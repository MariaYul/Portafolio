package com.telcel.gsrh.solicitudcurso.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CAP_REGION")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator", strategy = "assigned")
public class Region extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = -1879395065633415324L;

  /*  @OneToMany(targetEntity=Usuario.class, mappedBy="region")
    private Set<Usuario> usuarios;*/
    
    @Transient
    private Serializador serializador = new Serializador();

   /* public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        serializador.deserializar(in, this);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        serializador.serializar(out, this);
    }
}
