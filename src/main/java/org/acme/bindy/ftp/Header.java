package org.acme.bindy.ftp;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@RegisterForReflection
//@FixedLengthRecord(length=128, paddingChar=' ')
//@FixedLengthRecord(length=20, paddingChar=' ')
@FixedLengthRecord
public class Header {

    @DataField(pos = 1, length=4)
    public String version;

    @DataField(pos = 5, length=4)
    public String lonCabecera;

    @DataField(pos = 9, length=3)
    public String tipoServicio;

    @DataField(pos = 12, length=9)
    public String longTotalTrama;

    @DataField(pos = 21, length=22)
    public String fragmentacion;

    @DataField(pos = 43, length=9)
    public String ttl;

    @DataField(pos = 52, length=1)
    public String tipoConsulta;

    @DataField(pos = 53, length=16)
    public String caractVerif;

    @DataField(pos = 69, length=10)
    public String codInstitucion;

    @DataField(pos = 79, length=10)
    public String codServerReniec;

    @DataField(pos = 89, length=10)
    public String agenciaInstSolic;

    @DataField(pos = 99, length=10)
    public String usuarioFinalInst;

    @DataField(pos = 109, length=10)
    public String hostFinalInst;

    @DataField(pos = 119, length=10)
    public String reservado;

    @DataField(pos = 129, length=1000)
    public String subTrama;

    public String getTramaHeader() {
        return version + lonCabecera + tipoServicio + longTotalTrama + fragmentacion + ttl + tipoConsulta + caractVerif + codInstitucion + codServerReniec + agenciaInstSolic + usuarioFinalInst + hostFinalInst + reservado;
    }


    @Override
        public String toString() {
            return "";
        }

}
