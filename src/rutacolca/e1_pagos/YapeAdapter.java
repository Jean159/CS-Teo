package rutacolca.e1_pagos;

public class YapeAdapter implements PasarelaPago {
    private final YapeClient client = new YapeClient();

    @Override
    public ResultadoPago cobrar(Reserva reserva, String cuenta) {
        long centimos = reserva.total().movePointRight(2).longValueExact();
        String op = client.pagar(cuenta, centimos);
        if (op == null) {
            return new ResultadoPago(false, null, "Transaccion Yape denegada");
        }
        return new ResultadoPago(true, op, "Pago exitoso via Yape");
    }
}
