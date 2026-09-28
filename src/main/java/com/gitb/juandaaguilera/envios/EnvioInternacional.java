package com.gitb.juandaaguilera.Envios;

public class EnvioInternacional implements Envio {

    public static final double TARIFA_KG = 12000;
    public static final double IMPUESTO = 0.10;
    public static final double SEGURO = 0.05;

    private String codigo;
    private String destinatario;
    private double peso;
    private String paisDestino;

    public EnvioInternacional(String codigo, String destinatario, double peso, String paisDestino) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
        this.paisDestino = paisDestino;
    }

    @Override
    public double calcularCosto() {
        double costoBase = this.peso * TARIFA_KG;
        double impuesto = costoBase * IMPUESTO;
        double seguro = costoBase * SEGURO;
        return costoBase + impuesto + seguro;
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getDestinatario() {
        return this.destinatario;
    }

    @Override
    public double getPeso() {
        return this.peso;
    }

    @Override
    public String getTipo() {
        return "Internacional";
    }

    @Override
    public String getDatoAdicional() {
        return this.paisDestino;
    }

    public String getPaisDestino() {
        return this.paisDestino;
    }
}
