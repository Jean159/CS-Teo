package rutacolca.e1_pagos;

/**
 * Nuevo adaptador incorporado para demostrar el Principio Abierto/Cerrado (OCP).
 */
public class PlinAdapter implements PasarelaPago {
    @Override
    public ResultadoPago cobrar(Reserva reserva, String cuenta) {
        return new ResultadoPago(true, "PLIN-" + cuenta.substring(5), "Pago exitoso via Plin");
    }
}
