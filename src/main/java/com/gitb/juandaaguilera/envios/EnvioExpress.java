package com.gitb.juandaaguilera.Envios;

public class EnvioExpress implements Envio {

    public static final double TARIFA_KG = 5000;
    public static final double RECARGO_PRIORIDAD = 15000;

    private String codigo;
    private String destinatario;
    private double peso;
    private String horaLimite;

    public EnvioExpress(String codigo, String destinatario, double peso, String horaLimite) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
        this.horaLimite = horaLimite;
    }

    @Override
    public double calcularCosto() {
        return this.peso * TARIFA_KG + RECARGO_PRIORIDAD;
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
        return "Express";
    }

    @Override
    public String getDatoAdicional() {
        return this.horaLimite;
    }

    public String getHoraLimite() {
        return this.horaLimite;
    }
}
