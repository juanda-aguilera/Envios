package com.gitb.juandaaguilera.Envios;

import java.util.ArrayList;

public class Registros {

    private ArrayList<Envio> envios;

    public Registros() {
        this.envios = new ArrayList<>();
    }

    public String agregarEnvio(Envio envio) {
        if (this.existeCodigo(envio.getCodigo())) {
            return "Ya existe un envio con el codigo " + envio.getCodigo();
        }
        this.envios.add(envio);
        return "Envio registrado con exito";
    }

    public boolean existeCodigo(String codigo) {
        for (Envio e : this.envios) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Envio> obtenerEnvios() {
        return this.envios;
    }

    public double calcularTotal() {
        double total = 0;
        for (Envio e : this.envios) {
            total += e.calcularCosto();
        }
        return total;
    }
}
