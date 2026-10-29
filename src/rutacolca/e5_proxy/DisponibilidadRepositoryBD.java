package rutacolca.e5_proxy;

import java.util.concurrent.atomic.AtomicInteger;

public class DisponibilidadRepositoryBD implements DisponibilidadRepository {
    private final AtomicInteger consultasBD = new AtomicInteger(0);

    @Override
    public int cuposLibres(long tourSalidaId) {
        consultasBD.incrementAndGet();
        return 14;
    }

    public int getConsultasRealizadas() {
        return consultasBD.get();
    }
}
