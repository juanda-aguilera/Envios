package com.gitb.juandaaguilera.Envios;

public interface Envio {

    double calcularCosto();

    String getCodigo();

    String getDestinatario();

    double getPeso();

    String getTipo();

    String getDatoAdicional();
}
