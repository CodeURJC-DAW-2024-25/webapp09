# tRIpPiNs

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

**• Tipos de usuarios**: La aplicación web deberá considerar tres tipos de usuarios:
   ◦ Usuario anónimo: Dicho del usuario que inicia la pagina web sin realizar el registro, por lo tanto no podria reservar ningun alojamiento.
   ◦ Usuario registrado: Tipo de usuario que al iniciar sesion con sus credenciales podria buscar alojamientos, reservar en los mismos y recibir confirmaciones por correo.
   ◦ Usuario administrador: Tipo de usuario que inicia sesion con credenciales de administrador, encargado del funcionamiento de la web, este debe habilitar alojamientos.


