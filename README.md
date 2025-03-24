# TriPPins

# Miembros del grupo
- Sergio Villagarcía Sánchez |  mailto:s.villagarcia.2019@alumnos.urjc.es | Sergio-1502
- Guillermo Blázquez Barbacid |  mailto:g.blazquez.2019@alumnos.urjc.es  | Blazk0o
- Juan Antonio Alcalde Yuste |  mailto:ja.alcalde.2021@alumnos.urjc.es | Jantoniio3
- Steven Adrados Khunliang |  mailto:s.adrados.2020@alumnos.urjc.es  | Jadrados
- Rubén Alcojor Patilla |  mailto:r.alcojor.2022@alumnos.urjc.es  | rualpa2004

# Instrucciones de ejecución:

**Fase 1**
- Necesario:JAVA versión JDK 21, MYSQL versión 8.0.33 , "http://maven.apache.org/POM/4.0.0" , y de springboot la extensión de visualStudio org.springframework.boot versión 3.4.3  
- Necesario clonar el repositorio de github,  
- Tener docker abierto y loggeado,
- Introducir el comando en VisualStudio; docker run --rm -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=Trippins -p 3307:3306 -d mysql:8.0.33
- Abrirlo en el puerto seguro 8443.
**Fase 2**
- En la terminal escribir Docker-compose up
- Ir a localhost:443 para ver nuestra pagina web
- En caso de estar en la maquina virtual:
    - Ir a la carpeta donde esta el proyecto
    - ssh -i ssh-keys/appWeb09.key vmuser@10.100.139.215
    - git clone del repositorio
    - docker-compose up
    - docker run {nombre del contenedor}

# Participación de los miembros

A continuación, se detalla la participación de cada miembro del equipo, incluyendo sus commits más importantes y las clases en las que han trabajado.

## **--Fase 2---**

## **Sergio Villagarcía Sánchez**
- **Correo**: [s.villagarcia.2019@alumnos.urjc.es](mailto:s.villagarcia.2019@alumnos.urjc.es)
- **Usuario GitHub**: [Sergio-1502](https://github.com/Sergio-1502)

### **Commits más importantes**
1. 
2.
3.
4. 
5.  


---

## **Guillermo Blázquez Barbacid**
- **Correo**: [g.blazquez.2019@alumnos.urjc.es](mailto:g.blazquez.2019@alumnos.urjc.es)
- **Usuario GitHub**: [Blazk0o](https://github.com/Blazk0o)

### **Commits más importantes**
1. 
2.
3. 
4. 
5. 


---

## **Juan Antonio Alcalde Yuste**
- **Correo**: [ja.alcalde.2021@alumnos.urjc.es](mailto:ja.alcalde.2021@alumnos.urjc.es)
- **Usuario GitHub**: [Jantoniio3](https://github.com/Jantoniio3)

### **Commits más importantes**
1. Commit:[e5364c425dd7537b46d3ff910e15d75606df598b](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/e5364c425dd7537b46d3ff910e15d75606df598b)
   Descripcion:Crear imagenes docker, contenedor de la aplicacion y la imagen de la aplicacion
2. Commit:[884441ca6070a55c26618bd9f8354aa594de5999](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/884441ca6070a55c26618bd9f8354aa594de5999)
   Descripcion:crear reviewDTO
3. Commit:[340d2d90cddfcddd5df6771ed9901bf6b008044c](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/340d2d90cddfcddd5df6771ed9901bf6b008044c)
   Descripcion:creando api-docs. Crea el yaml pero no crea la documentacion
4. 
5. 



---

## **Steven Adrados Khunliang**
- **Correo**: [s.adrados.2020@alumnos.urjc.es](mailto:s.adrados.2020@alumnos.urjc.es)
- **Usuario GitHub**: [Jadrados](https://github.com/Jadrados)

### **Commits más importantes**
1. Commit:[0878b1f8107e3e773bf912c616ad496bdea6fa8c](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/0878b1f8107e3e773bf912c616ad496bdea6fa8c)
   Descripcion:Incluida toda la seguridad de jwt por tokens, te devuelve el token y el rol o roles del user
2. Commit:[db8105be0556165c2bb5783eb43b6d8bde72c36c](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/db8105be0556165c2bb5783eb43b6d8bde72c36c)
   Descripcion:ya estala api rest de review
3. Commit:[653c5a6739c872b97935cf27ef176ec534feefe1](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/653c5a6739c872b97935cf27ef176ec534feefe1)
   Descripcion:ya funciona la api rest de las casas
4. Commit:[97fa6c5de631a6fbb5589070c170371aa67d3574](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/97fa6c5de631a6fbb5589070c170371aa67d3574)
   Descripcion:API de users ya funciona
5. Commit:[1e20834394eb8cf5e7f6972bc364628c65760543](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/1e20834394eb8cf5e7f6972bc364628c65760543)
   Descripcion:tags introducidas en housing api rest


## **Rubén Alcojor Patilla**
- **Correo**: [r.alcojor.2022@alumnos.urjc.es](mailto:r.alcojor.2022@alumnos.urjc.es)
- **Usuario GitHub**: [rualpa2004](https://github.com/rualpa2004)

### **Commits más importantes**
1. 
2. 
3. 
4. 
5. 


# Modelo ER
![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Modelo_ER_terminado.png?raw=true)


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

En una plataforma tipo reservas de alojamientos, los permisos de los usuarios se dividen en tres categorías principales: **Usuario Anónimo**, **Usuario Registrado** y **Usuario Administrador**. A continuación, se describen los permisos de cada uno:



### **1. Usuario Anónimo**  
- **Descripción:** Es un usuario que accede a la plataforma sin registrarse ni iniciar sesión.  
- **Permisos:**  
  - Puede buscar y visualizar alojamientos.  
  - Puede leer reseñas y opiniones de otros usuarios.  
  - No puede realizar reservas ni escribir reseñas.  
  - No tiene acceso a funciones personalizadas, como guardar favoritos o recibir confirmaciones por correo.  
- **Entidades que posee:** Ninguna. 




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
  - Reseñas publicadas.
  - Historial de reservas.



### **3. Usuario Administrador**  
- **Descripción:** Es un usuario con privilegios elevados, encargado de gestionar y mantener la plataforma.  
- **Permisos:**  
  - Puede habilitar o deshabilitar alojamientos, restaurantes y actividades en la plataforma.  
  - Puede moderar reseñas y eliminar contenido inapropiado.  
  - Puede acceder a estadísticas y reportes de uso de la plataforma.  
  - Puede gestionar usuarios (bloquear, eliminar o cambiar roles).  
  - Puede configurar y actualizar las categorías, etiquetas y filtros de búsqueda.  
  - Puede enviar notificaciones masivas a los usuarios registrados.
  - Puede gestionar las peticiones de reserva de los usuarios registrados.
- **Entidades que posee:**  
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
   - **Descripción**: Este gráfico mostrará las valoraciones de los usuarios en forma de una barra de 0 a 100.  
   - **Uso**: Los usuarios podrán ver de un vistazo la distribución de las valoraciones de un alojamiento o servicio, lo que les ayudará a tomar decisiones informadas basadas en la opinión de otros clientes.  
   - **Formato**: Gráfico de barras o circular para representar el porcentaje de cada color con respecto a las reseñas.



# Pantallas

1. **Pantalla inicial**
   
   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Principal.png?raw=true)

   Es la pantalla inicial de nuestra aplicación. Desde ella se inicia la navegación.
   
2. **Inicio de sesión**

   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Inicio%20Sesi%C3%B3n.png?raw=true)

   Esta pantalla sirve para iniciar sesión mediante tu nombre de usuario y tu contraseña en la cuenta que tengas creada.
   
3. **Crear cuenta**

   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Crear%20cuenta.png?raw=true)

   Esta pantalla corresponde a la creación de una cuenta, donde tienes que introducir datos como usuario, contraseña, correo, DNI, etc.
   
4. **Equipo**

   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Equipo.png?raw=true)

   En esta pantalla aparecen los nombres del equipo de desarrollo de la web.
   
5. **Contacto**

   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Contacto.png?raw=true)

   Pantalla que sirve para poder ponerse en contacto con los miembros del equipo de desarrollo para cualquier tipo de gestión.
   
6. **Habitaciones**

   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Habitaciones.png?raw=true)
   
   Pantalla donde se pueden ver las habitaciones disponibles en la página para reservar.
   
7. **Reservas**
   
   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Reservas.png?raw=true)
   
   En esta pantalla se realizan las reservas de la habitación que se desee.
   Hay que introducir datos como el número y tipo de personas que van a ir (Adultos o niños) y las fechas de entrada y salida.
   
8. **Reseñas**
   
   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Rese%C3%B1as.png?raw=true)
   
   En esta pantalla se muestran reseñas acerca de las habitaciones realizadas por antiguos clientes.
   
9. **Administración**
   
   ![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Admin.png?raw=true)
   
   Esta pantalla está únicamente disponible para los usuarios que administren la página.
   En ella estarán mensajes con la información de las solicitudes de reserva y serán los administradores los que acepten o rechacen las mismas.

# Diagrama de flujo
![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Diagrama%20de%20flujo%20arreglado.png?raw=true)

# Diagrama de clases
![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Diagrama%20de%20clases.png?raw=true)
