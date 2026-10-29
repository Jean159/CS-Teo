package rutacolca.e5_proxy;

public class EjecutarE5 {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("  RUTACOLCA - ESCENARIO E5: PATRON PROXY DE CACHE (TTL 30s)       ");
        System.out.println("==================================================================");

        long salidaId = 501L;

        System.out.println("\n[1] Prueba SIN Proxy: 300 peticiones directas a BD:");
        DisponibilidadRepositoryBD bdSin = new DisponibilidadRepositoryBD();
        BuscadorController ctrlSin = new BuscadorController(bdSin);
        for (int i = 0; i < 300; i++) ctrlSin.cupos(salidaId);
        int hitsSin = bdSin.getConsultasRealizadas();
        System.out.println("  -> Consultas directas recibidas por la BD: " + hitsSin);

        System.out.println("\n[2] Prueba CON Proxy: 300 peticiones en 60s con TTL de 30s:");
        DisponibilidadRepositoryBD bdCon = new DisponibilidadRepositoryBD();
        DisponibilidadProxyCache proxy = new DisponibilidadProxyCache(bdCon, 30_000L);

        long t0 = 1000000L;
        for (int s = 0; s < 60; s++) {
            long t = t0 + (s * 1000L);
            for (int r = 0; r < 5; r++) proxy.cuposLibresConTimestamp(salidaId, t);
        }
        int hitsCon = bdCon.getConsultasRealizadas();
        System.out.println("  -> Consultas recibidas por la BD con Proxy: " + hitsCon);
        double red = ((double)(hitsSin - hitsCon) / hitsSin) * 100.0;
        System.out.printf("  -> Reduccion del trafico a la BD: %.2f%%\n", red);

        System.out.println("\n[3] Consulta final de confirmacion directa a BD: " + bdCon.cuposLibres(salidaId) + " cupos.");
        System.out.println("\n[OK] Escenario E5 ejecutado con exito.");
    }
}
