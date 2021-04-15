#language: es
@EjemplosDeAutomatizacionEnApis
 Característica: Automatización en apis
    Como usuario
    Quiero redactar y automatizar apis
    Para obtener valores y respuestas de las solicitudes enviadas

 //   @CatFactsHappyPath
 //   Escenario: Solicitud de consulta sobre las cosas de gatos
 //     Dado que configuro las cabeceras
 //       | cabeceras | valor |
 //       | Authorization | Bearer 47b013ba-722a-46b2-8ec4-c818516474fe |
 //     Cuando ejecuto el servicio con la configuracion de las cabeceras
 //       | servicio | url | metodo | formato |
 //       | CatFacts | https://cat-fact.herokuapp.com/facts/ | GET | json |
 //     Entonces valido si el codigo de respuesta es "200"
 //     Y guardo la respuesta de la ejecucion del servicio

   @SearchNameMiddle
   Escenario: Solicitud de consulta para paginas de Universidades
     Dado que configuro las cabeceras
       | cabeceras | valor |
       | Authorization | Brearer 47b013ba-722a-46b2-8ec4-c818516474fe |
     Cuando ejecuto el servicio con la configuracion de las cabeceras
       | servicio | url | metodo | formato |
       | SearchNameMiddle | http://universities.hipolabs.com/search?name=middles | GET | json |
       Entonces valido si el codigo de respuesta es "200"
     Y guardo la respuesta de la ejecucion del servicio

   @SearchNameMiddleError
   Escenario: Solicitud de consulta para paginas de Universidades con ERROR
     Dado que configuro las cabeceras
       | cabeceras | valor |
       | Authorization | Brearer 47b013ba-722a-46b2-8ec4-c818516474fe |
     Cuando ejecuto el servicio con la configuracion de las cabeceras
       | servicio | url | metodo | formato |
       | SearchNameMiddle | http://universities.hipolabs.com/searchs?name=middles | GET | json |
     Entonces valido si el codigo de respuesta es "404"
     Y guardo la respuesta de la ejecucion del servicio