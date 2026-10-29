package rutacolca.e1_pagos;

import java.math.BigDecimal;

public class EjecutarE1 {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("  RUTACOLCA - ESCENARIO E1: PATRON ADAPTADOR DE MEDIOS DE PAGO    ");
        System.out.println("==================================================================");

        ReservaService service = new ReservaService();
        Reserva r1 = new Reserva(101L, new BigDecimal("120.00"), "Carlos Mamani");
        Reserva r2 = new Reserva(102L, new BigDecimal("85.50"), "Lucia Caceres");
        Reserva r3 = new Reserva(103L, new BigDecimal("375.00"), "John Miller");

        System.out.println("\n[1] Procesamiento con adaptadores iniciales:");
        service.pagar(r1, new TarjetaAdapter(), "4111222233334444");
        service.pagar(r2, new YapeAdapter(), "959123456");
        service.pagar(r3, new PaypalAdapter(), "john.miller@travel.com");

        System.out.println("\n[2] Demostracion de beneficio OCP (Nuevo metodo Plin sin tocar ReservaService):");
        Reserva r4 = new Reserva(104L, new BigDecimal("60.00"), "Valeria Quispe");
        service.pagar(r4, new PlinAdapter(), "958001122");

        System.out.println("\n[OK] Escenario E1 ejecutado con exito.");
    }
}
