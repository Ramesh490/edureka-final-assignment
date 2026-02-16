package com.edureka.order.controller;

import com.edureka.order.entity.Order;
import com.edureka.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;


    @Test
    void testGetOrderById() throws Exception {

        Order order = new Order();
        order.setId(1L);
        order.setCategory("Electronics");
        order.setQuantity(1);
        order.setStatus("PLACED");

        when(orderService.getById(1L)).thenReturn(order);

        mockMvc.perform(get("/api/orders/{id}", 5L))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.category").value("ELECTRONICS"))
        .andExpect(jsonPath("$.status").value("PLACED"));
    }
}
