workspace "RutaColca" "Arquitectura E4: PayPal dentro del monolito modular" {
    !identifiers hierarchical
    model {
        turista = person "Turista" "Busca salidas, reserva y paga."
        agencia = person "Operador de agencia" "Gestiona tours y reservas, incluso sin conexión."
        rutacolca = softwareSystem "RutaColca" "Reserva tours, cobra boletos y entrega itinerarios." {
            web = container "Web del turista" "Busca salidas y solicita reservas/pagos." "React SPA"
            portal = container "Portal de agencias" "Administra tours y reservas con trabajo sin conexión." "React PWA"
            api = container "API Core" "Monolito modular de reservas, pagos y boletos." "Java 21 / Spring Boot" {
                iniciar = component "IniciarPago" "Crea intento vinculado a reserva y proveedor autorizado." "Java"
                puerto = component "ProveedorPago" "Contrato de inicio y consulta de pagos." "Interfaz Java"
                actualAdapter = component "AdaptadorPasarelaActual" "Traduce respuestas de la pasarela existente." "Java / HTTPS"
                paypalAdapter = component "AdaptadorPayPal" "Traduce API y avisos del proveedor PayPal." "Java / HTTPS"
                confirmar = component "ConfirmarPago" "Verifica aviso, referencia y estado; confirma idempotentemente." "Java"
            }
            db = container "PostgreSQL" "Salidas, reservas, pagos y claves idempotentes." "PostgreSQL 16" "Database"
        }
        pasarela = softwareSystem "Pasarela de pagos vigente" "Procesa pagos actuales." "Externo"
        paypal = softwareSystem "PayPal" "Proveedor propuesto para turistas extranjeros; contrato pendiente." "Externo"
        autocolca = softwareSystem "AUTOCOLCA" "Valida boletos turísticos." "Externo"
        whatsapp = softwareSystem "WhatsApp Business" "Envía itinerarios y avisos." "Externo"
        turista -> rutacolca.web "Busca salidas y paga" "HTTPS"
        agencia -> rutacolca.portal "Gestiona tours y reservas" "HTTPS"
        rutacolca.web -> rutacolca.api "Solicita reserva e inicio de pago" "JSON/HTTPS"
        rutacolca.portal -> rutacolca.api "Administra tours y reservas" "JSON/HTTPS"
        rutacolca.api -> rutacolca.db "Guarda reservas y estados" "JDBC/SQL"
        rutacolca.api -> pasarela "Procesa pagos vigentes" "HTTPS"
        rutacolca.api -> paypal "Inicia y consulta pagos propuestos" "HTTPS"
        paypal -> rutacolca.api "Notifica cambio de estado autenticado" "HTTPS"
        rutacolca.api -> autocolca "Valida boletos" "HTTPS"
        rutacolca.api -> whatsapp "Envía itinerarios" "HTTPS"
        rutacolca.api.iniciar -> rutacolca.api.puerto "Solicita inicio de pago" "Java"
        rutacolca.api.puerto -> rutacolca.api.actualAdapter "Delega a pasarela vigente" "Java"
        rutacolca.api.puerto -> rutacolca.api.paypalAdapter "Delega a PayPal" "Java"
        rutacolca.api.paypalAdapter -> paypal "Inicia y consulta intento" "HTTPS"
        paypal -> rutacolca.api.confirmar "Notifica resultado autenticado" "HTTPS"
        rutacolca.api.confirmar -> rutacolca.api.paypalAdapter "Consulta estado para conciliar" "Java"
        rutacolca.api.iniciar -> rutacolca.db "Registra intento y referencia" "JDBC/SQL"
        rutacolca.api.confirmar -> rutacolca.db "Actualiza reserva idempotentemente" "JDBC/SQL"
    }
    views {
        systemContext rutacolca "ContextoE4" {
            include *
            autolayout lr
        }
        container rutacolca "ContenedoresE4" {
            include *
            autolayout lr
        }
        component rutacolca.api "ComponentesPagosE4" {
            include *
            autolayout lr
        }
        dynamic rutacolca.api "DinamicoPagoE4" {
            turista -> rutacolca.web "Selecciona PayPal y reserva"
            rutacolca.api.iniciar -> rutacolca.api.puerto "Solicita inicio de pago"
            rutacolca.api.puerto -> rutacolca.api.paypalAdapter "Delega a PayPal"
            rutacolca.api.paypalAdapter -> paypal "Inicia y consulta intento"
            rutacolca.api.iniciar -> rutacolca.db "Registra intento y referencia"
            paypal -> rutacolca.api.confirmar "Notifica resultado autenticado"
            rutacolca.api.confirmar -> rutacolca.api.paypalAdapter "Consulta estado para conciliar"
            rutacolca.api.confirmar -> rutacolca.db "Actualiza reserva idempotentemente"
            autolayout lr
        }
        styles {
            element "Person" {
                shape Person
                background #0B3D62
                color #ffffff
            }
            element "Software System" {
                background #1B5E8B
                color #ffffff
            }
            element "Container" {
                background #0E7D73
                color #ffffff
            }
            element "Database" {
                shape Cylinder
                background #0E7D73
                color #ffffff
            }
            element "Externo" {
                background #666666
                color #ffffff
            }
        }
    }
}
