package rutacolca.e1_pagos;

import java.math.BigDecimal;

public record Reserva(long id, BigDecimal total, String titular) {}
