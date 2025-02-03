# TriPPins

# Miembros del grupo
- Sergio Villagarcía Sánchez |  mailto:s.villagarcia.2019@alumnos.urjc.es | Sergio-1502
- Guillermo Blázquez Barbacid |  mailto:g.blazquez.2019@alumnos.urjc.es  | Blazk0o
- Juan Antonio Alcalde Yuste |  mailto:ja.alcalde.2021@alumnos.urjc.es | Jantoniio3
- Steven Adrados Khunliang |  mailto:s.adrados.2020@alumnos.urjc.es  | Jadrados
- Rubén Alcojor Patilla |  mailto:r.alcojor.2022@alumnos.urjc.es  | rualpa2004
#Modelo ER
![image](https://github.com/user-attachments/assets/ca00a60e-88c0-4de9-9e96-51ff54eaa894)

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

# Permisos de los Usuarios

En una plataforma tipo **TripAdvisor**, los permisos de los usuarios se dividen en tres categorías principales: **Usuario Anónimo**, **Usuario Registrado** y **Usuario Administrador**. A continuación, se describen los permisos de cada uno:



### **1. Usuario Anónimo**  
- **Descripción:** Es un usuario que accede a la plataforma sin registrarse ni iniciar sesión.  
- **Permisos:**  
  - Puede buscar y visualizar alojamientos, restaurantes y actividades.  
  - Puede leer reseñas y opiniones de otros usuarios.  
  - No puede realizar reservas ni escribir reseñas.  
  - No tiene acceso a funciones personalizadas, como guardar favoritos o recibir confirmaciones por correo.  
- **Entidades que posee:** Ninguna. No tiene datos asociados en la base de datos, salvo cookies de sesión temporal.  



### **2. Usuario Registrado**  
- **Descripción:** Es un usuario que se ha registrado en la plataforma e inicia sesión con sus credenciales.  
- **Permisos:**  
  - Puede buscar y visualizar alojamientos, restaurantes y actividades.  
  - Puede realizar reservas y recibir confirmaciones por correo electrónico.  
  - Puede escribir y publicar reseñas sobre sus experiencias.  
  - Puede guardar alojamientos o actividades en una lista de favoritos.  
  - Puede recibir recomendaciones personalizadas basadas en sus búsquedas y reservas anteriores.  
  - Puede modificar o cancelar reservas (dentro de los plazos permitidos).  
- **Entidades que posee:**  
  - Datos personales (nombre, correo electrónico, contraseña, etc.).  
  - Historial de reservas.  
  - Lista de favoritos.  
  - Reseñas publicadas.  



### **3. Usuario Administrador**  
- **Descripción:** Es un usuario con privilegios elevados, encargado de gestionar y mantener la plataforma.  
- **Permisos:**  
  - Puede habilitar o deshabilitar alojamientos, restaurantes y actividades en la plataforma.  
  - Puede moderar reseñas y eliminar contenido inapropiado.  
  - Puede acceder a estadísticas y reportes de uso de la plataforma.  
  - Puede gestionar usuarios (bloquear, eliminar o cambiar roles).  
  - Puede configurar y actualizar las categorías, etiquetas y filtros de búsqueda.  
  - Puede enviar notificaciones masivas a los usuarios registrados.  
- **Entidades que posee:**  
  - Acceso completo a la base de datos.  
  - Control sobre todas las entidades de la plataforma (alojamientos, reseñas, usuarios, etc.).  



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

# Imágenes

Las imágenes son un componente esencial en nuestra plataforma, ya que permiten a los usuarios visualizar y comprender mejor los servicios ofrecidos. A continuación, se detallan las entidades que pueden incluir imágenes, junto con su propósito específico:

1. **Perfil**:  
   - **Requerimiento**: Cada perfil de usuario debe incluir una imagen.  
   - **Propósito**: La imagen de perfil ayuda a personalizar la experiencia del usuario, facilitando la identificación y generando confianza en las interacciones dentro de la plataforma.

2. **Alojamiento (Post)**:  
   - **Requerimiento**: Cada publicación de alojamiento debe incluir imágenes.  
   - **Propósito**: Las imágenes muestran los interiores, exteriores y detalles generales del alojamiento, permitiendo a los usuarios evaluar visualmente la estancia antes de realizar una reserva. Esto mejora la transparencia y la toma de decisiones.

# Gráficos

Los gráficos son una herramienta visual clave para mejorar la experiencia del usuario, permitiéndole comprender y analizar información de manera rápida y efectiva. A continuación, se presentan dos posibles candidatos para implementar en la plataforma:

1. **Valoración**:  
   - **Descripción**: Este gráfico mostrará las valoraciones de los usuarios en forma de estrellas (por ejemplo, de 1 a 5 estrellas).  
   - **Uso**: Los usuarios podrán ver de un vistazo la distribución de las valoraciones de un alojamiento o servicio, lo que les ayudará a tomar decisiones informadas basadas en la opinión de otros clientes.  
   - **Formato**: Gráfico de barras o circular para representar el porcentaje de cada nivel de estrellas con respecto a las reseñas.

2. **Precio**:  
   - **Descripción**: Este gráfico actuará como un selector de precios, mostrando los precios medios de las estancias disponibles en función de los criterios de búsqueda.  
   - **Uso**: Los usuarios podrán ajustar su búsqueda seleccionando un rango de precios, lo que facilitará la identificación de opciones que se ajusten a su presupuesto.  
   - **Formato**: Gráfico de rango (slider) o histograma para visualizar la distribución de precios.
