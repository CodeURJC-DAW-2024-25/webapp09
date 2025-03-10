# TriPPins

# Miembros del grupo
- Sergio Villagarcía Sánchez |  mailto:s.villagarcia.2019@alumnos.urjc.es | Sergio-1502
- Guillermo Blázquez Barbacid |  mailto:g.blazquez.2019@alumnos.urjc.es  | Blazk0o
- Juan Antonio Alcalde Yuste |  mailto:ja.alcalde.2021@alumnos.urjc.es | Jantoniio3
- Steven Adrados Khunliang |  mailto:s.adrados.2020@alumnos.urjc.es  | Jadrados
- Rubén Alcojor Patilla |  mailto:r.alcojor.2022@alumnos.urjc.es  | rualpa2004

# Instrucciones de ejecución:
- Necesario:JAVA versión JDK 21, MYSQL versión 8.0.33 , "http://maven.apache.org/POM/4.0.0" , y de springboot la extensión de visualStudio org.springframework.boot versión 3.4.3  
- Necesario clonar el repositorio de github,  
- Tener docker abierto y loggeado,
- Introducir el comando en VisualStudio; docker run --rm -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=Trippins -p 3307:3306 -d mysql:8.0.33
- Abrirlo en el puerto seguro 8443.

# Participación de los miembros

A continuación, se detalla la participación de cada miembro del equipo, incluyendo sus commits más importantes y las clases en las que han trabajado.

---

## **Sergio Villagarcía Sánchez**
- **Correo**: [s.villagarcia.2019@alumnos.urjc.es](mailto:s.villagarcia.2019@alumnos.urjc.es)
- **Usuario GitHub**: [Sergio-1502](https://github.com/Sergio-1502)

### **Commits más importantes**
1. **Creada ReviewAjax.js**:
   - Commit: [429d223](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/429d223815b6cb621f0c8d0384a52ba16329e246)
   - Descripción: creada la clase ReviewAjax.js.
2. **Editado el controler de Ajax**:
   - Commit: [f27c1c5](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/f27c1c5f338f4a79e4332ec5eb51e9165fdb0627)
   - Descripción: Añadido en CustomAjaxController.java el @PostMapping referido a reviewAjax.js.
3. **Implementación de pagina de error**:
   - Commit: [f8e63fb](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/f8e63fb5555324d6eab92348dd3ae5ec641e6061#diff-6d62feebe3adfa742594ab46b714b3cbd4b53da4a745b1d7364e6764d193cc05)
   - Descripción: Se ha añadido una pagina de error.
4. **Implementación de los graficos de rating**:
   - Commit: [3bcb0b1](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/3bcb0b1e8525898ea2df1b8a7df629291b8ed5dc)
   - Descripción: Se ha implemtado una barra en los comentarios, puntuación.
5. **Arreglos en rating**:
   - Commit: [836019b](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/836019bf834fe3950e29c4e1cfe8a6836c94233d)
   - Descripción: Se implementan arreglos en la la barra de valoración de los comentarios para tener 3 colores y una apariencia más idónea.  


### **Clases más trabajadas**
- [reviewAjax.js](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/429d223815b6cb621f0c8d0384a52ba16329e246/practica1/src/main/resources/static/reviewAjax.js)
- [CustomAjaxControler.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/f8e63fb5555324d6eab92348dd3ae5ec641e6061/practica1/src/main/java/es/grupo9/practica1/CustomAjaxController.java)
- [error.html](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/f8e63fb5555324d6eab92348dd3ae5ec641e6061/practica1/src/main/resources/templates/error.html)
- [style.css](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/TestSinCSRF/practica1/src/main/resources/static/style.css)
- [roomDetails.html](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/TestSinCSRF/practica1/src/main/resources/templates/roomDetails.html)

---

## **Guillermo Blázquez Barbacid**
- **Correo**: [g.blazquez.2019@alumnos.urjc.es](mailto:g.blazquez.2019@alumnos.urjc.es)
- **Usuario GitHub**: [Blazk0o](https://github.com/Blazk0o)

### **Commits más importantes**
1. **Implementación del repositorio de Review**:
   - Commit: [699406a](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/699406ab069b62fdd4a4a840c5c327e8a19a65fb)
   - Descripción: Creadas las clases ReviewService y ReviewRepository aunque todavia no he inicializado la clase, tiene que unirse a housin, user y reservation
.
2. **Implementada la clase reseñas**:
   - Commit: [df8fdc8](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/df8fdc87a0ebfe32d83eac7db0b211a848210695)
   - Descripción: creada la clase reseñas.
3. **Admin y controler**:
   - Commit: [7ba43dc](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/7ba43dc8e6105896214dd28be1be1d174bf3615b)
   - Descripción: añadido en admin aceptar alojamientos y linkeada la pagina.
4. **Cambio de las rutas**:
   - Commit: [ee99418](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/ee99418522a66f3acffaff210d0c2d546cc79435)
   - Descripción: cambiadas las rutas.
5. **Implementación de constructores vacios**:
   - Commit: [79c674a](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/79c674af51725dc6158254e614ae6c6c866a05cb)
   - Descripción: arreglados los fallos de Mysql, necesidad de constructores vacios.

### **Clases más trabajadas**
- [Review.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/java/es/grupo9/practica1/Review.java)  
- [ReviewService.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/java/es/grupo9/practica1/ReviewService.java)  
- [ReviewRepository.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/java/es/grupo9/practica1/ReviewRepository.java)  
- [Moustachecontroler.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/java/es/grupo9/practica1/MustacheController.java)  
- [admin.html](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/resources/templates/admin.html)  
---

## **Juan Antonio Alcalde Yuste**
- **Correo**: [ja.alcalde.2021@alumnos.urjc.es](mailto:ja.alcalde.2021@alumnos.urjc.es)
- **Usuario GitHub**: [Jantoniio3](https://github.com/Jantoniio3)

### **Commits más importantes**
1. **Implementación de user**:
   - Commit: [637d218](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/637d218eafca5e53c428f4b6cba27963dea210bd)
   - Descripción: Edita el controler de mustache,usuario y registro.
2. **Implementación de alojamientos**:
   - Commit: [f0429d4](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/f0429d45f7245b2ce6e96c05683bd35eb5e9c9e8)
   - Descripción: Ya funcionan los post de alojamientos.
3. **Implementación de la barra de busqueda**:
   - Commit: [88c08ed](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/88c08ed6ae6af1a16d380d1aacc59b034e64ef8c)
   - Descripción: Crear barra de busqueda avanzada, añadir pagina de admin desde admin.
4. **Solucion de problemas**:
   - Commit: [6513d23](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/6513d238fff2b82b6bcaa36e724403297b08d18a)
   - Descripción: eliminar algunas paginas, arreglar el problema de login que cree en el commit anterior, mostrar y esconder password de login y registro.
5. **Implementación de email**:
   - Commit: [354ea03](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/354ea03ea162a1790d9f6328f1fa4389dc499c79)
   - Descripción: Trabajando en emailservice, envio de emails automaticos.

### **Clases más trabajadas**
- [HousingService.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/f0429d45f7245b2ce6e96c05683bd35eb5e9c9e8/practica1/src/main/java/es/grupo9/practica1/HousingService.java)
- [passwordtoogle.js](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/6513d238fff2b82b6bcaa36e724403297b08d18a/practica1/src/main/resources/static/passwordtoogle.js)
- [EmainService.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/354ea03ea162a1790d9f6328f1fa4389dc499c79/practica1/src/main/java/es/grupo9/practica1/EmailService.java)
- [ControllerHelper.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/java/es/grupo9/practica1/ControllerHelper.java)
- [ReservationService.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/main/practica1/src/main/java/es/grupo9/practica1/ReservationService.java)

---

## **Steven Adrados Khunliang**
- **Correo**: [s.adrados.2020@alumnos.urjc.es](mailto:s.adrados.2020@alumnos.urjc.es)
- **Usuario GitHub**: [Jadrados](https://github.com/Jadrados)

### **Commits más importantes**
1. **Implementando Ajax**:
   - Commit: [014a566](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/014a56650afec257ed4d501a63d753764be8aee6)
   - Descripción: Clases de Ajax.
2. **Implementado Los correos automaticos**:
   - Commit: [
09750be](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/09750bea537d7d3767915fdf9bd2fdb3aba17f9e#diff-6d62feebe3adfa742594ab46b714b3cbd4b53da4a745b1d7364e6764d193cc05)  
   - Descripción: Correos y ajax ya funcionan solo hay que repetir el proceso para el resto ajax.
3. **Implementados cambios en pagina admin**:
   - Commit: [7ba43dc](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/7ba43dc8e6105896214dd28be1be1d174bf3615b)
   - Descripción: Metido que el admin pueda aceptar en la pagina web las reservas.
4. **Arreglado el controler**:
   - Commit: [cf4e60c](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/cf4e60cf59ae4bf2933bf375082cc25e48a4a7ad)
   - Descripción: BBDD operativa recibiendo datos solo falta el resto de htmls y clases.
5. **Personalizada el template**:
   - Commit: [c28ce6e](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/c28ce6e7ad8d4ea0f8f8a83659504e8d326a2cf1)
   - Descripción: Cambiadas las referencias y el template.

### **Clases más trabajadas**
- [MustacheControler.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/637d218eafca5e53c428f4b6cba27963dea210bd/practica1/src/main/java/es/grupo9/practica1/MustacheController.java)
- [adminAjax.js](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/014a56650afec257ed4d501a63d753764be8aee6/practica1/src/main/resources/static/adminAjax.js)  
- [MustacheControler.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/014a56650afec257ed4d501a63d753764be8aee6/practica1/src/main/java/es/grupo9/practica1/MustacheController.java)  
- [CustomAjaxControler.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/014a56650afec257ed4d501a63d753764be8aee6/practica1/src/main/java/es/grupo9/practica1/CustomAjaxController.java)  
- [admin.html](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/014a56650afec257ed4d501a63d753764be8aee6/practica1/src/main/resources/templates/admin.html)  

---

## **Rubén Alcojor Patilla**
- **Correo**: [r.alcojor.2022@alumnos.urjc.es](mailto:r.alcojor.2022@alumnos.urjc.es)
- **Usuario GitHub**: [rualpa2004](https://github.com/rualpa2004)

### **Commits más importantes**
1. **Implementado el CSRF**:
   - Commit: [6497a3b](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/6497a3ba73aaacf704e5ef29e8055105fe449f37)
   - Descripción: Se ha implementado la seguridad.
2. **Implementado los tokens**:
   - Commit: [0b4979f](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/0b4979f88e78f498ab84aae651c749561588c074)
   - Descripción: Añadiendo los tokens en los formularios.
3. **Cambiando todo a inglés**:
   - Commit: [45bae2b](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/45bae2b772f219867c7352dc49f1f05bd0cfc38c)
   - Descripción: Modificado todo a inglés.
4. **Guardando DNI**:
   - Commit: [0e7d9e1](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/0e7d9e1100ec4886cb65076c07db7c1090c47466)
   - Descripción: DNI guardado y validado correctamente.
5. **Arreglo de contraseña**:
   - Commit: [5f8e033](https://github.com/CodeURJC-DAW-2024-25/webapp09/commit/5f8e0336c961631359d18b612c61c3d0b1618590)
   - Descripción: Arreglo de la variable contraseña.

### **Clases más trabajadas**
- [profile.html](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/6497a3ba73aaacf704e5ef29e8055105fe449f37/practica1/src/main/resources/templates/profile.html)
- [MustacheController.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blame/6497a3ba73aaacf704e5ef29e8055105fe449f37/practica1/src/main/java/es/grupo9/practica1/MustacheController.java)
- [Housing.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/45bae2b772f219867c7352dc49f1f05bd0cfc38c/practica1/src/main/java/es/grupo9/practica1/Housing.java)
- [Reservation.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/45bae2b772f219867c7352dc49f1f05bd0cfc38c/practica1/src/main/java/es/grupo9/practica1/Reservation.java)
- [User.java](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/0e7d9e1100ec4886cb65076c07db7c1090c47466/practica1/src/main/java/es/grupo9/practica1/Usuario.java)



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
   - **Descripción**: Este gráfico mostrará las valoraciones de los usuarios en forma de estrellas (por ejemplo, de 1 a 5 estrellas).  
   - **Uso**: Los usuarios podrán ver de un vistazo la distribución de las valoraciones de un alojamiento o servicio, lo que les ayudará a tomar decisiones informadas basadas en la opinión de otros clientes.  
   - **Formato**: Gráfico de barras o circular para representar el porcentaje de cada nivel de estrellas con respecto a las reseñas.



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
![](https://github.com/CodeURJC-DAW-2024-25/webapp09/blob/main/practica1/src/main/resources/static/img/Diagrama%20de%20flujo.png?raw=true)
