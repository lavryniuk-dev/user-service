package com.startup.controller;

import java.util.List;
import com.startup.dto.request.UserRequestDto;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldReturnErrorsList() {
        MockMvcResponse response = RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new UserRequestDto("A", "  ", -1))
                .when()
                .post("/users")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<String> errors = response.path("errors");
        Assertions.assertTrue(errors.size() == 3
                && errors.contains("name" + System.lineSeparator() + "size must be between 2 and 2147483647")
                && errors.contains("surname" + System.lineSeparator() + "must not be blank")
                && errors.contains("age" + System.lineSeparator() + "must be greater than or equal to 0"));
    }
}
