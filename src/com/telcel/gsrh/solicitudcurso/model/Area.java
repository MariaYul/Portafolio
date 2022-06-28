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
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ARCHDIG_AREA")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator"
    , strategy = "sequence"
    , parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_AREA"))
public class Area extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = 8407192922790418657L;
    
    public static final int CLAVE_AREA_PERSONAL = 4;

    @OneToMany(targetEntity=TipoDocumento.class, mappedBy="area")
    private Set<TipoDocumento> tiposDocumento;
    
    @OneToMany(targetEntity=Usuario.class, mappedBy="area")
    private Set<Usuario> usuarios;
    
    @Transient
    private Serializador serializador = new Serializador();
    
    public Set<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(Set<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        serializador.deserializar(in, this);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        serializador.serializar(out, this);
    }
}
