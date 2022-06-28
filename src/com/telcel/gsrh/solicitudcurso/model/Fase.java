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
@Table(name="ARCHDIG_FASE")
@AttributeOverride(name="clave", column=@Column(name="CLAVE"))
@GenericGenerator(name="generator"
    , strategy = "sequence"
    , parameters = @Parameter(name="sequence", value="ARCHDIG_SEQ_FASE"))
public class Fase extends AbstractIntegerIdEntityModel implements Serializable {

    private static final long serialVersionUID = 3939861365227101943L;

    @OneToMany(targetEntity=TipoDocumento.class,mappedBy="fase")
    private Set<TipoDocumento> tiposDocumento;
    
    @Transient
    private Serializador serializador = new Serializador();
    
    public Set<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(Set<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        serializador.deserializar(in, this);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        serializador.serializar(out, this);
    }
}
