package rutacolca.e1_pagos;

/**
 * SDK simulado de tarjeta de credito/debito.
 */
public class TarjetaSdk {
    public int autorizar(String numeroTarjeta, long montoCentimos) {
        return (numeroTarjeta != null && numeroTarjeta.startsWith("4")) ? 0 : 402;
    }
}
