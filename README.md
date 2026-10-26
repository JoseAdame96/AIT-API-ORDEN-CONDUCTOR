# AIT-API-ORDEN-CONDUCTOR
API de prueba para la gestión de conductores y órdenes

## Pre requisitos

- **JDK 21**: Necesario para ejecutar el proyecto.
- **Maven 3.9.x**: Para la gestión de dependencias y construcción del proyecto.
- **MySQL**: Requerido para la conexión a base de datos


## Configuración del Proyecto

1. En su gestor de base de datos, ejecute el script `orders.sql` ubicado en resources. Considere utilizar los siguientes datos para la conexión:
    ```
    url:127.0.0.1
    username:root
    password:test1234
    ```
2. En una terminal, ingrese el siguiente comando para ejecutar el proyecto:
    ```
    mvn spring-boot:run
    ```