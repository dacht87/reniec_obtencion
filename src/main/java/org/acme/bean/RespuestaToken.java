package org.acme.bean;

public class RespuestaToken {

    public String version;

    public String lonCabecera;

    public String tipoServicio;

    public String longTotalTrama;

    public String fragmentacion;

    public String TTL;

    public String tipoConsulta;

    public String caractVerif;

    public String codInstitucion;

    public String codServerReniec;

    public String agenciaInstSolic;

    public String usuarioFinalInst;

    public String hostFinalInst;

    public String reservado;

    public String nroDNI;

    public String tipoSubConsulta;

    public String formatoFirma;

    public String reservadoSubTrama;

    //public String token;

    public RespuestaToken(){
    }

    @Override
    public String toString() {
        return "";
    }

    public String getTramaHeader() {
        return version + lonCabecera + tipoServicio + longTotalTrama + fragmentacion + TTL + tipoConsulta + caractVerif + codInstitucion + codServerReniec + agenciaInstSolic + usuarioFinalInst + hostFinalInst + reservado;
    }



}
