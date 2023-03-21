package com.startup.controller;

import com.startup.dto.request.UserRequestDto;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldCreateNewUser() {
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new UserRequestDto("Ashley", "Medina", 31))
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Ashley"))
                .body("surname", Matchers.equalTo("Medina"))
                .body("age", Matchers.equalTo(31));
    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldReturnValidUser() {
        RestAssuredMockMvc.when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(2))
                .body("name", Matchers.equalTo("Lucinda"))
                .body("surname", Matchers.equalTo("Cochran"))
                .body("age", Matchers.equalTo(35));
    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldReturnAllValidUsers() {
        RestAssuredMockMvc.when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("Ethan"))
                .body("[0].surname", Matchers.equalTo("Carter"))
                .body("[0].age", Matchers.equalTo(20))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].name", Matchers.equalTo("Lucinda"))
                .body("[1].surname", Matchers.equalTo("Cochran"))
                .body("[1].age", Matchers.equalTo(35))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].name", Matchers.equalTo("Derick"))
                .body("[2].surname", Matchers.equalTo("Tanner"))
                .body("[2].age", Matchers.equalTo(47));
    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldUpdateValidUser() {
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new UserRequestDto("Stacy", "Jennings", 21))
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Stacy"))
                .body("surname", Matchers.equalTo("Jennings"))
                .body("age", Matchers.equalTo(21));
    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldDeleteValidUser() {
        RestAssuredMockMvc.when()
                .delete("/users/2")
                .then()
                .statusCode(200);

        RestAssuredMockMvc.when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("Ethan"))
                .body("[0].surname", Matchers.equalTo("Carter"))
                .body("[0].age", Matchers.equalTo(20))
                .body("[1].id", Matchers.equalTo(3))
                .body("[1].name", Matchers.equalTo("Derick"))
                .body("[1].surname", Matchers.equalTo("Tanner"))
                .body("[1].age", Matchers.equalTo(47));
    }
}
