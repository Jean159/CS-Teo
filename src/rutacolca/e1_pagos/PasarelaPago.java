package rutacolca.e1_pagos;

/**
 * Interfaz objetivo (Target) del patron Adaptador.
 */
public interface PasarelaPago {
    ResultadoPago cobrar(Reserva reserva, String cuenta);
}
