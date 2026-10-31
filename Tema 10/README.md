# AG-10 · RutaColca · Grupo 04

Caso original de la guía AG-10. Escenario asignado: **E4, aceptar PayPal**, con meta ≤ 3 días-persona y 0 cambios en el dominio.

**Repositorio:** https://github.com/Jean159/CS-Teo/tree/main/Tema%2010

## Mapa del repositorio

```text
Tema 10/
├── docs/architecture/
│   ├── adr/                         ADR-002 (E3) y ADR-E5 de referencia
│   ├── c4/ruta-colca/               workspace.dsl + 4 .puml + 4 .png
│   └── ruta-colca/ag10/             E1, E2, E4, E7, cuestionario y verificación
├── src/pagos/                       Prototipo Java 21 y su prueba
├── README.md
└── .gitignore
```

**Sobre las rutas:** la guía exige literalmente `docs/architecture/ruta-colca/ag10/` para E1/E2/E4/E7 y `docs/architecture/c4/ruta-colca/workspace.dsl` para E5. Esas dos rutas se conservan para que la entrega sea evaluable. Todo lo demás se aplanó: las exportaciones C4 y sus PNG viven en la misma carpeta que el DSL, y el prototipo Java usa `src/pagos/` en vez de `src/main/java/edu/unsa/rutacolca/pagos/`.

## Entregables

| Entrega | Archivo |
|---|---|
| E1 | `docs/architecture/ruta-colca/ag10/utilidad.md` |
| E2 | `docs/architecture/ruta-colca/ag10/alternativas.md` |
| E3 | `docs/architecture/adr/ADR-002-proveedores-de-pago.md` |
| E4 | `docs/architecture/ruta-colca/ag10/evaluacion-cruzada.md` + `docs/architecture/adr/ADR-E5-REF-validacion-autocolca.md` |
| E5 | `docs/architecture/c4/ruta-colca/workspace.dsl` + `contexto|contenedores|componentes|dinamica .puml/.png` |
| E6 | `src/pagos/` (incluye `ServicioPagosTest.java`) |
| E7 | `docs/architecture/ruta-colca/ag10/bitacora-ia.md` |
| Cuestionario | `docs/architecture/ruta-colca/ag10/cuestionario.md` |
| Verificación | `docs/architecture/ruta-colca/ag10/verificacion-equipo.md` |

## Comprobación

La prueba está en `src/pagos/ServicioPagosTest.java`. Para ejecutarla sin el script local:

```bash
tmp="$(mktemp -d)"
javac -d "$tmp" src/pagos/*.java
java -cp "$tmp" pagos.ServicioPagosTest
rm -rf "$tmp"
```

- La prueba ejecuta **7 comprobaciones** con dobles en memoria y no procesa pagos reales ni usa la API PayPal.
- Structurizr CLI v2025.11.09: el DSL valida sin errores. La exportación C4-PlantUML produjo cuatro `.puml`; PlantUML los renderizó a PNG. Los PNG están en la misma carpeta C4.
- El informe PDF/DOCX se entrega aparte y no forma parte de esta copia de GitHub.

## Restricciones pendientes

El `workspace.dsl` editable y el proyecto Spring de producción de AG-08 **no están disponibles**. El DSL es una reconstrucción coherente con AG-10. No hay contrato PayPal, sandbox/credenciales, pasarela vigente ni webhook real: E4 no se ha medido en días-persona y la integración de producción requiere autorización, pruebas de contrato, verificación de avisos y transacciones persistentes. El ADR-E5-REF se incluye como referencia para la evaluación cruzada.
