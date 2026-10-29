package rutacolca.e1_pagos;

import java.math.BigDecimal;

/**
 * SDK simulado de PayPal (opera en USD).
 */
public class PaypalSdk {
    public String crearOrden(String emailPaypal, BigDecimal dolares) {
        return (emailPaypal != null && emailPaypal.contains("@")) ? "PAYPAL-ORD-" + Math.abs(emailPaypal.hashCode()) : null;
    }
}
