//1- Pacote
package petstore;

//2- Bíblíotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

//3- Classe
public class Pet {

   //3.1- Atributo
   String uri = "https://petstore.swagger.io/v2/pet"; //endreço da entidade pet

   //3.2- Métodos e Funções
   public String lerJson(String caminhoJson) throws IOException {

      return new String(Files.readAllBytes(Paths.get(caminhoJson)));

   }

   //Incluir - Create - Post
   @Test //Identifica o metodo ou função como um teste para testNG
   public void incluirPet() throws IOException {
      String jsonBody = lerJson("db/Pet1.json");

      //Sintax Gherkin
      //Dado - Quando - Então
      // Given - When - Then

      given() //Dado
              .contentType("application/json") // comum em API REST - Antigas era "text/xml"
              .log().all()
              .body(jsonBody)
     .when() // Quando
              .post(uri)
     .then() // Então
              .log().all()
              .statusCode(200)
              .body("name", is("Luffy"))
              .body("status", is("available"))
              .body("category.name", is("dog"))
              .body("tags.name", contains("sta"))

      ;



   }
}