# Nuvu Customers APP!

-   Maven Project
-   Java 8
-   Spring boot framework
-   Spring oauth2 framework
-   JPA
-   H2


#### ENDPOINTS (Rest services) (v2)

-   **Solicitud de token/login**
>**POST**
>
host:/oauth/token?grant_type=password&username={{user}}&password={{password}}
AUTHORIZATION: type:Basic Auth | username=nuvu-app | password=secret

-   **Registro de usuario**
>**POST**
>
host:/user/create

-   **Consulta de usuario**
>**GET**
>
host:/private/user?access_token={{token}}

-   **Actualización de usuario**
>**PUT**
>
host:/private/user/update?access_token={{token}}

-   **Asignación de tarjeta de crédito a usuario**
>**POST**
>
host:/private/user/cc/assign?access_token={{token}}

-   **Actualización de tarjeta de crédito de usuario**
>**PUT**
>
host:/private/user/cc/update?access_token={{token}}

-   **Consultar todos los usuarios (ADMIN)**
>**GET**
>
host:/private/user/all?access_token={{admin_token}}

-   **Consultar todas las tdc (ADMIN)**
>**GET**
>
host:/private/user/cc/all?access_token={{admin_token}}

#### CÓDIGOS DE RESPUESTA - ENDPOINTS (v2)
***USUARIO***

| CODIGO | MENSAJE |
|--|--|
| 100 | User has been created |
| 101 | User has been updated |
| -100 | User not found |
| -101 | Missing user information |
| -102 | Missing information for user registration (username, password, firstName, lastName, idType, idNumber, age) are required |
| -103 | User name (####) already exist in DB |
| -104 | Error creating user - errMsg:#### |
| -105 | Missing user information for update |
| -106 | User name (####) not found in DB |
| -107 | Missing information for user updating (firstName, lastName, idType, idNumber, age. are required) |
| -108 | Error updating user - errMsg: #### |

***TARJETA DE CREDITO***

| CODIGO | MENSAJE |
|--|--|
| 200 | Credit card assigned to user |
| 201 | Credit card updated |
| -200 | Missing credit card information |
| -201 | Missing information for credit card assignment (all fields are required) |
| -202 | User already have a credit card assigned |
| -203 | Credit card already assigned to another user |
| -204 | Error assigning credit card - errMsg:#### |
| -205 | Missing information for credit card updating (all fields are required |
| -206 | User does not have a credit card assigned |
| -207 | Error updating credit card - errMsg: #### |

#### BASE DE DATOS

![DB](https://i.ibb.co/xHSJkJL/Screenshot-from-2020-10-11-13-34-59.png)
