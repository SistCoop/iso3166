# iso3166 - restfull services

Es un paquete que permite administrar datos de países segun el estandar iso3166.

> Revisar iso3166 para mayor informacion.

### Resources
* CountryCodeResource ("/countryCodes")


| Method        | URL                                               | Descripcion                                   |
| ------------- |:-------------------------------------------------:| ---------------------------------------------:|
| GET           | "/countryCodes/alpha2Code/{alpha2Code}"           | busca un CountryCode segun alpha2Code         |
| GET           | "/countryCodes/alpha2Code/{alpha3Code}"           | busca un CountryCode segun alpha3Code         |
| GET           | "/countryCodes/alpha2Code/{numericCode}"          | busca un CountryCode segun numericCode        |
| POST          | "/countryCodes"                                   | crea un CountryCode segun el objeto añadido   |
| PUT           | "/countryCodes/alpha2Code/{alpha2Code}"           | Actualiza segun alpha2Code                    |
| PUT           | "/countryCodes/alpha3Code/{alpha3Code}"           | Actualiza segun alpha3Code                    |
| PUT           | "/countryCodes/alpha2Code/{numericCode}"          | Actualiza segun numericCode                   |
| DELETE        | "/countryCodes/alpha2Code/{alpha2Code}"           | elimina un CountryCode segun alpha2Code       |
| DELETE        | "/countryCodes/alpha2Code/{alpha3Code}"           | elimina un CountryCode segun alpha3Code       |
| DELETE        | "/countryCodes/alpha2Code/{numericCode}"          | elimina un CountryCode segun numericCode      |
| GET           | "/countryCodes?filterText&firstResult&maxResults" | Busca segun los parametros enviados           |


Los objetos countryCodes tienen la siguiente estructura:

{
   alpha2Code: String,
   alpha3Code: String,
   numericCode: String,    
   independent: Boolean,
   status: String,  
   shortNameEn: String,
   shortNameUppercaseEn: String,
   fullNameEn: String,
}

### Version
1.0.0

### Tecnologías

Dependences:

* [JavaEE] - javaEE

Este proyecto es mantenido por SistCoop S.A.C.