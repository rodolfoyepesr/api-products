# api-users
API RESTful para crear usuarios

Para probar la API REST seguir estos pasos:
1. Clonar el proyecto api-users en local
2. Correr este proyecto con Spring Boot App
3. Una vez levantado ir a Postman o cualquier herramienta para testear APIs
4. Seleccionar el metodo POST y agregar la URL /users/add
5. ingresar el JSON request respetando el siguiente formato:
{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "Hunter2@",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}   
8. Se creara un registro en la base de datos en memoria H2 con la informacion enviada y retornara un status 201 y el JSON de respuestas como el siguiente:
   {
    "id": "c2f26bd9-d516-41d3-9b54-d0c27f519d35",
    "created": "2023-09-23T16:33:48.523+00:00",
    "modified": null,
    "lastLogin": "2023-09-23T16:33:48.523+00:00",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJqd3QiLCJzdWIiOiJyb2RvbGZvLnllcGVzQG5pc3VtLm9yZyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2OTU0ODY4MjgsImV4cCI6MTY5NTQ4NzQyOH0.6XSUETuPRm9LFvJJK4d4CJ_gTgMWNv21vBIGiUX2xhTOZ41Mzyv9pYKA2c-UNQyzNiz_J-Ewn428b7MrruIESA",
    "isactive": "ACTIVE"
}
