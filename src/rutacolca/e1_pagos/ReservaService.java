package rutacolca.e1_pagos;

public class ReservaService {
    public ResultadoPago pagar(Reserva r, PasarelaPago pasarela, String cuenta) {
        ResultadoPago resultado = pasarela.cobrar(r, cuenta);
        if (!resultado.exitoso()) {
            throw new RuntimeException("Fallo en el pago: " + resultado.mensaje());
        }
        System.out.println("  [ReservaService] Reserva #" + r.id() + " procesada -> Ref: " + resultado.codigoReferencia() + " (" + resultado.mensaje() + ")");
        return resultado;
    }
}
