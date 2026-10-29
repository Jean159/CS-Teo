package rutacolca.e5_proxy;

public class BuscadorController {
    private final DisponibilidadRepository repository;

    public BuscadorController(DisponibilidadRepository repository) {
        this.repository = repository;
    }

    public int cupos(long salidaId) {
        return repository.cuposLibres(salidaId);
    }
}
