package rutacolca.e1_pagos;

/**
 * SDK simulado de Yape.
 */
public class YapeClient {
    public String pagar(String celular, long centimos) {
        return (celular != null && celular.length() == 9) ? "YP-" + celular.substring(5) + "-" + centimos : null;
    }
}
