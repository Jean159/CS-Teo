package rutacolca.e3_notificaciones;

import rutacolca.e1_pagos.Reserva;

public interface ObservadorReserva {
    void alConfirmar(Reserva r);
}
