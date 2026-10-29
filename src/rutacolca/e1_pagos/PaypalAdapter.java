package rutacolca.e1_pagos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaypalAdapter implements PasarelaPago {
    private final PaypalSdk sdk = new PaypalSdk();
    private static final BigDecimal TC = new BigDecimal("3.75");

    @Override
    public ResultadoPago cobrar(Reserva reserva, String cuenta) {
        BigDecimal dolares = reserva.total().divide(TC, 2, RoundingMode.HALF_UP);
        String ordenId = sdk.crearOrden(cuenta, dolares);
        if (ordenId == null) {
            return new ResultadoPago(false, null, "Error al generar orden en PayPal");
        }
        return new ResultadoPago(true, ordenId, "Pago en USD ($ " + dolares + ")");
    }
}
