# TriPPins

# Miembros del grupo
- Sergio Villagarcía Sánchez
- Guillermo Blázquez Barbacid
- Juan Antonio Alcalde Yuste
- Steven Adrados Khunliang
- Rubén Alcojor Patilla

# Algoritmo

Para obtener datos similares a búsquedas anteriores en función de etiquetas comunes, se utilizarán **queries JOIN** en la base de datos. El proceso se basa en identificar registros que compartan etiquetas o categorías comunes con las búsquedas previas. A continuación, se describe el enfoque:

1. **Identificación de etiquetas comunes**:  
   Se analizan las etiquetas asociadas a las búsquedas anteriores y se comparan con las etiquetas de otros registros en la base de datos.

2. **Uso de JOIN**:  
   Se aplicará un **INNER JOIN** entre las tablas que contienen los datos y las etiquetas, utilizando como clave de unión el identificador de las etiquetas. Esto permitirá filtrar los registros que compartan etiquetas con las búsquedas anteriores.

3. **Filtrado y ordenación**:  
   Los resultados se filtrarán para mostrar solo aquellos registros con un número mínimo de etiquetas comunes y se ordenarán según la relevancia (por ejemplo, el número de etiquetas coincidentes).

4. **Devolución de resultados**:  
   Finalmente, se devolverán los datos similares a las búsquedas anteriores, priorizando aquellos con mayor coincidencia de etiquetas.

Este enfoque garantiza que los resultados estén directamente relacionados con las búsquedas previas, mejorando la precisión y utilidad de la información recuperada.

# Tipos de usuarios 
La aplicación web deberá considerar tres tipos de usuarios:
   - **1. Usuario Anónimo**
-Descripción: Es un usuario que accede a la plataforma sin registrarse ni iniciar sesión.

Permisos:

-Puede buscar y visualizar alojamientos, restaurantes y actividades.

-Puede leer reseñas y opiniones de otros usuarios.

-No puede realizar reservas ni escribir reseñas.

-No tiene acceso a funciones personalizadas, como guardar favoritos o recibir confirmaciones por correo.

-Entidades que posee: Ninguna. No tiene datos asociados en la base de datos, salvo cookies de sesión temporal.


   -** 2. Usuario Registrado**
-Descripción: Es un usuario que se ha registrado en la plataforma e inicia sesión con sus credenciales.

-Permisos:

-Puede buscar y visualizar alojamientos, restaurantes y actividades.

-Puede realizar reservas y recibir confirmaciones por correo electrónico.

-Puede escribir y publicar reseñas sobre sus experiencias.

-Puede guardar alojamientos o actividades en una lista de favoritos.

-Puede recibir recomendaciones personalizadas basadas en sus búsquedas y reservas anteriores.

-Puede modificar o cancelar reservas (dentro de los plazos permitidos).

Entidades que posee:

-Datos personales (nombre, correo electrónico, contraseña, etc.).

-Historial de reservas.

-Lista de favoritos.

-Reseñas publicadas.
   - **3. Usuario administrador:**
   - Descripción: Es un usuario con privilegios elevados, encargado de gestionar y mantener la plataforma.

Permisos:

-Puede habilitar o deshabilitar alojamientos, restaurantes y actividades en la plataforma.

-Puede moderar reseñas y eliminar contenido inapropiado.

-Puede acceder a estadísticas y reportes de uso de la plataforma.

-Puede gestionar usuarios (bloquear, eliminar o cambiar roles).

-Puede configurar y actualizar las categorías, etiquetas y filtros de búsqueda.

-Puede enviar notificaciones masivas a los usuarios registrados.

Entidades que posee:

-Acceso completo a la base de datos.

-Control sobre todas las entidades de la plataforma (alojamientos, reseñas, usuarios, etc.).

# Tecnologías Complementarias

Para mejorar la experiencia del cliente, implementaremos un **sistema automatizado de creación y envío de correos electrónicos** que proporcionará a los usuarios los detalles de sus reservas y servicios contratados a través de la página. Este sistema se basará en las siguientes tecnologías y procesos:

1. **Generación de correos personalizados**:  
   Utilizaremos plantillas dinámicas para generar correos electrónicos personalizados que incluyan información específica de cada reserva, como fechas, horarios, servicios contratados y datos del cliente.

2. **Integración con servicios de correo**:  
   Se integrará un servicio de envío de correos electrónicos, como **SMTP** o APIs de proveedores como SendGrid o Mailgun, para garantizar la entrega eficiente y confiable de los mensajes.

3. **Automatización del proceso**:  
   El sistema estará programado para enviar automáticamente los correos en momentos clave, como:  
   - Confirmación de reserva.  
   - Recordatorios previos al servicio.  
   

4. **Notificaciones en tiempo real**:  
   En caso de cambios o actualizaciones en las reservas, el sistema enviará notificaciones inmediatas para mantener informados a los clientes.

Este sistema complementario no solo mejorará la comunicación con los clientes, sino que también aumentará la satisfacción y confianza en los servicios ofrecidos a través de la página.

