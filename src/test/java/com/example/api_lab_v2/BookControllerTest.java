package com.example.api_lab_v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//classe para Testes de Unidade
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCriarLivro() throws Exception {
        Book livro = new Book();
        livro.setTitulo("Memórias do subsolo");

        mockMvc.perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Memórias do subsolo"));
    }

    @Test
    void testLivro() throws Exception {
        mockMvc.perform(get("/api/book/teste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Aqua Tofana"));
    }

    @Test
    void testBuscarLivroPorId() throws Exception {
        int bookId = 123;
        mockMvc.perform(get("/api/book/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Livro com id: " + bookId));
    }

    @Test
    void testBuscarLivroPorTitulo() throws Exception {
        String titulo = "Lady Killers";
        mockMvc.perform(get("/api/book/search")
                        .param("titulo", titulo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Você pesquisou pelo título: " + titulo));
    }
}
