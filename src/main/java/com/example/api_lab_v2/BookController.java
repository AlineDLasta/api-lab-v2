package com.example.api_lab_v2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {
    @PostMapping("/book")
    public Book criarLivro(@RequestBody Book livro){
        System.out.println("Livro recebido: " + livro.getTitulo());
        return livro;
    }

    @GetMapping("/book/teste")
    public Book livro() {
        Book livro = new Book();
        livro.setTitulo("Aqua Tofana");
        return livro;
    }
}
