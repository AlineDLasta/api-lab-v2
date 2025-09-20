package com.example.api_lab_v2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//classe para Testes de Integração
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIT {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testLivro() {
        ResponseEntity<Book> response = template.getForEntity("/api/book/teste", Book.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getTitulo()).isEqualTo("Aqua Tofana");
    }

    @Test
    public void testbuscarLivroPorId() {
        int bookId = 42;
        ResponseEntity<Book> response = template.getForEntity("/api/book/" + bookId, Book.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getTitulo()).isEqualTo("Livro com id: " + bookId);
    }
}
