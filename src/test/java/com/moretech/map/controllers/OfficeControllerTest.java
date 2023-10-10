package com.moretech.map.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moretech.map.exception.CheckException;
import com.moretech.map.schemas.Point;
import com.moretech.map.schemas.TaskListRequest;
import com.moretech.map.service.SearchOptimalServiceImpl;
import com.moretech.map.utils.CheckRequest;
import com.moretech.map.utils.EndPoint;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SearchOptimalServiceImpl service;
    @Autowired
    private CheckRequest checkRequest;

    @Schema(description = "Тест на POST запрос с корректным телом запроса")
    @Test
    public void goodPostRequest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new TaskListRequest("56.800584, 60.675637", true, true, true));
        System.out.println(json);

        this.mockMvc.perform(post(EndPoint.api + EndPoint.check)
                        .contentType("application/json")
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Schema(description = "Тест на POST запрос с не корректным телом запроса(пустые координаты)")
    @Test
    public void badPostRequest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new TaskListRequest("", true, true, true));

        this.mockMvc.perform(post(EndPoint.api + EndPoint.check)
                        .contentType("application/json")
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CheckException));
    }

    @Schema(description = "Тест на POST запрос с не корректным телом запроса(не правильные координаты)")
    @Test
    public void voidPostRequest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new TaskListRequest("56.803205 57673256", true, true, true));

        this.mockMvc.perform(post(EndPoint.api + EndPoint.check)
                        .contentType("application/json")
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CheckException));
    }

    @Schema(description = "Тест на POST запрос с корректным телом запроса")
    @Test
    public void goodGetRequest() throws Exception {

        String coordinates = "?coordinates=60.497874,56.926760";

        this.mockMvc.perform(get(EndPoint.api + EndPoint.all +coordinates)
                    )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Schema(description = "Тест на GET запрос с не корректным телом запроса(пустые координаты)")
    @Test
    public void badGetRequest() throws Exception {


        String coordinates = "?coordinates= ";;

        this.mockMvc.perform(get(EndPoint.api + EndPoint.all + coordinates))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CheckException));
    }

    @Schema(description = "Тест на GET запрос с не корректным телом запроса(не правильные координаты)")
    @Test
    public void voidGetRequest() throws Exception {

        String coordinates = "?coordinates=60.497874 56.926760";

        this.mockMvc.perform(get(EndPoint.api + EndPoint.all + coordinates))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CheckException));
    }
}

/*
POST http://localhost:8080/api/check
Content-Type: application/json

{
  "point": "56.800584, 60.675637",
  "cardIssue": true,
  "withdrawCash": true,
  "currencyExchange":true
}


GET http://localhost:8080/api/all
Content-Type: application/json

{
  "coordinates":"56.800584, 60.675637",
  "latitude": 56,
  "decLatitude": 800584,
  "longitude":60,
  "decLongitude":675637
}
 */
