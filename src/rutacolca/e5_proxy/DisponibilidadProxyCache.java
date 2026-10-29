package rutacolca.e5_proxy;

import java.util.concurrent.ConcurrentHashMap;

public class DisponibilidadProxyCache implements DisponibilidadRepository {
    private final DisponibilidadRepositoryBD real;
    private final long ttlMillis;

    public record EntradaCache(int cupos, long timestamp) {
        public boolean esValida(long maxAgeMillis, long ahora) {
            return (ahora - timestamp) <= maxAgeMillis;
        }
    }

    private final ConcurrentHashMap<Long, EntradaCache> cache = new ConcurrentHashMap<>();

    public DisponibilidadProxyCache(DisponibilidadRepositoryBD real, long ttlMillis) {
        this.real = real;
        this.ttlMillis = ttlMillis;
    }

    @Override
    public int cuposLibres(long tourSalidaId) {
        return cuposLibresConTimestamp(tourSalidaId, System.currentTimeMillis());
    }

    public int cuposLibresConTimestamp(long tourSalidaId, long ahoraMillis) {
        EntradaCache entrada = cache.get(tourSalidaId);
        if (entrada != null && entrada.esValida(ttlMillis, ahoraMillis)) {
            return entrada.cupos();
        }
        int cuposFrescos = real.cuposLibres(tourSalidaId);
        cache.put(tourSalidaId, new EntradaCache(cuposFrescos, ahoraMillis));
        return cuposFrescos;
    }
}
