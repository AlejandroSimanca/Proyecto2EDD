# proyecto 2: sistema de gestion de impresion

## descripcion del proyecto
este proyecto consiste en un sistema que administra una cola de impresion universitaria usando un monticulo binario para priorizar documentos segun el tipo de usuario y el tiempo de envio. utiliza una tabla hash para gestionar el registro de usuarios y permitir busquedas eficientes de sus documentos asociados.

## autor
- alejandro simanca

## repositorio de github
url: https://github.com/AlejandroSimanca/Proyecto2EDD

## como ejecutar el sistema
1. clonar o descargar el proyecto de github.
2. abrir la carpeta en netbeans 8.2 o cualquier version mas nueva.
3. ejecutar el archivo proyecto2edd.java que es el que inicia la interfaz.
4. usar el boton de cargar para meter el archivo csv con los usuarios del sistema.

## analisis de complejidad
aca explico brevemente porque el programa es eficiente:

1. tabla hash: la usamos para los usuarios. buscar uno es o(1) porque el hash te lleva directo al dato sin dar vueltas por toda la lista.

2. monticulo binario: es lo que mueve la cola de prioridad. insertar y sacar el documento que sigue tarda o(log n), lo cual es super rapido comparado con ordenar una lista normal.

3. listas enlazadas: cada usuario tiene su lista de documentos personal. insertar ahi es o(1) porque siempre se agrega al final.

con estas estructuras el sistema puede manejar muchos datos sin ponerse lento.