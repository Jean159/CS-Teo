package rutacolca.e3_notificaciones;

import rutacolca.e1_pagos.Reserva;
import java.util.ArrayList;
import java.util.List;

public class PublicadorReservas {
    private final List<ObservadorReserva> suscriptores = new ArrayList<>();

    public void suscribir(ObservadorReserva o) { suscriptores.add(o); }
    public void notificar(Reserva r) {
        for (ObservadorReserva o : suscriptores) o.alConfirmar(r);
    }
}
