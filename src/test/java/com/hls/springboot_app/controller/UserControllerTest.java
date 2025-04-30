package com.hls.springboot_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hls.springboot_app.model.User;
import com.hls.springboot_app.repository.UserRepository;
import com.hls.springboot_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository; // ✅ ahora sí hace match

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setName("Luis");
        user.setEmail("bribiesca.luis@hlsgroup.com.mx");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Luis\",\"email\":\"bribiesca.luis@hlsgroup.com.mx\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Luis"))
                .andExpect(jsonPath("$.email").value("bribiesca.luis@hlsgroup.com.mx"));
    }
}

