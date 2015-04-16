# iso3166 - restfull services

Es un paquete que permite administrar datos de países segun el estandar iso3166.

> Revisar iso3166 para mayor informacion.

### Resources
* CountryCodeResource ("/countryCodes")


| Method        | URL                                               | Descripcion                       |
| :------------ |:--------------------------------------------------|:---------------------------------|
| GET           | "/countryCodes/alpha2Code/{alpha2Code}"           | Busca uno segun alpha2Code        |
| GET           | "/countryCodes/alpha2Code/{alpha3Code}"           | Busca uno segun alpha3Code        |
| GET           | "/countryCodes/alpha2Code/{numericCode}"          | Busca uno segun numericCode       |
| POST          | "/countryCodes"                                   | Crea uno                          |
| PUT           | "/countryCodes/alpha2Code/{alpha2Code}"           | Actualiza uno segun alpha2Code    |
| PUT           | "/countryCodes/alpha3Code/{alpha3Code}"           | Actualiza uno segun alpha3Code    |
| PUT           | "/countryCodes/alpha2Code/{numericCode}"          | Actualiza uno segun numericCode   |
| DELETE        | "/countryCodes/alpha2Code/{alpha2Code}"           | Elimina uno segun alpha2Code      |
| DELETE        | "/countryCodes/alpha2Code/{alpha3Code}"           | Elimina uno segun alpha3Code      |
| DELETE        | "/countryCodes/alpha2Code/{numericCode}"          | Elimina uno segun numericCode     |
| GET           | "/countryCodes?filterText&firstResult&maxResults" | Busca segun parametros            |


Los objetos countryCodes tienen la siguiente estructura:

```json
"countryCode": {
   "alpha2Code": "String",
   "alpha3Code": "String",
   "numericCode": "String",    
   "independent": "Boolean",
   "status": "String",  
   "shortNameEn": "String",
   "shortNameUppercaseEn": "String",
   "fullNameEn": "String"
}
```

### Version
1.0.0

### Tecnologías

Dependences:

* [JavaEE] - javaEE

Este proyecto es mantenido por SistCoop S.A.C.