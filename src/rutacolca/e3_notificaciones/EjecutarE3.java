package rutacolca.e3_notificaciones;

import rutacolca.e1_pagos.Reserva;
import java.math.BigDecimal;

public class EjecutarE3 {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("  RUTACOLCA - ESCENARIO E3: PATRON OBSERVADOR DE NOTIFICACIONES   ");
        System.out.println("==================================================================");

        PublicadorReservas eventos = new PublicadorReservas();
        eventos.suscribir(r -> System.out.println("  [WhatsApp -> Turista] Confirmacion enviada a " + r.titular() + " (Reserva #" + r.id() + ")"));
        eventos.suscribir(r -> System.out.println("  [WhatsApp -> Guia] Nueva asignacion para tour de " + r.titular()));
        eventos.suscribir(r -> System.out.println("  [Correo -> Agencia] Notificacion de pago por S/ " + r.total()));
        eventos.suscribir(r -> System.out.println("  [Extension -> Hospedaje] Bloquear habitacion en Chivay"));
        eventos.suscribir(r -> System.out.println("  [Extension -> Contabilidad] Asiento contable registrado"));

        Reserva reserva = new Reserva(8801L, new BigDecimal("240.00"), "Gabriela Mendoza");
        System.out.println("\nDisparando evento de confirmacion de reserva:");
        eventos.notificar(reserva);

        System.out.println("\n[OK] Escenario E3 ejecutado con exito.");
    }
}
