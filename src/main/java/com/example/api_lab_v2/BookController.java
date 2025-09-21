package com.example.api_lab_v2;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/book/{id}")
    public Book buscarLivroPorId(@PathVariable int id){
        Book livro = new Book();
        livro.setTitulo("Livro com id: " + id);
        return livro;
    }

    @GetMapping("/book/search")
    public Book buscarLivroPorTitulo(@RequestParam String titulo){
        Book livro = new Book();
        livro.setTitulo("Você pesquisou pelo título: " + titulo);
        return livro;
    }

    @GetMapping("/books")
    public List<Book> listarTodosOsLivros() {
        List<Book> listaDeLivros = new ArrayList<>();
        listaDeLivros.add(new Book().setTitulo("A morte de ivan ilitch").setId(1));
        listaDeLivros.add(new Book().setTitulo("A Metamorfose").setId(2));
        listaDeLivros.add(new Book().setTitulo("Dom Quixote").setId(3));
        return listaDeLivros;
    }
}
