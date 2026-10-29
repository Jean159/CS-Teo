package rutacolca.e1_pagos;

public class TarjetaAdapter implements PasarelaPago {
    private final TarjetaSdk sdk = new TarjetaSdk();

    @Override
    public ResultadoPago cobrar(Reserva reserva, String cuenta) {
        long centimos = reserva.total().movePointRight(2).longValueExact();
        int cod = sdk.autorizar(cuenta, centimos);
        if (cod != 0) {
            return new ResultadoPago(false, null, "Pago rechazado por tarjeta (codigo " + cod + ")");
        }
        return new ResultadoPago(true, "AUTH-TJ-" + centimos, "Aprobado con Tarjeta");
    }
}
