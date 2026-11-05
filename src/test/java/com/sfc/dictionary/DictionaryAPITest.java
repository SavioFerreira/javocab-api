package com.sfc.dictionary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DictionaryAPITest {

    @Test
    void existingWordReturnsTrue() {
        given()
          .when().get("/dictionary/word-exists/example")
          .then()
             .statusCode(200)
             .body("word", is("example"))
             .body("exists", is(true));
    }

    @Test
    void unknownWordReturnsFalse() {
        given()
          .when().get("/dictionary/word-exists/xyzqwk")
          .then()
             .statusCode(200)
             .body("exists", is(false));
    }

    @Test
    void optionLikeWordIsNotTreatedAsGrepFlag() {
        given()
          .when().get("/dictionary/word-exists/-V")
          .then()
             .statusCode(200)
             .body("exists", is(false));
    }
}
