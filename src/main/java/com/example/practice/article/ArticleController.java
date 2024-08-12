package com.example.practice.article;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("articles")

public class ArticleController {
    private final ArticleService service;

    public ArticleController (ArticleService service){
        this.service = service;
    }

    //create
    @GetMapping("create")
    public String createView(){
        return "articles/create.html";
    }

    @PostMapping("create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer
    ){
       Long id = service.create(title, content, writer).getId();
       return String.format("redirect:/articles/%d", id);

    }

    //read all

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("articles", service.readAll());
        return "articles/home.html";
    }

    //read one

    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article", service.readOne(id));
        return "articles/read.html";
    }

   //update

    @GetMapping("{id}/update")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article", service.readOne(id));
        return "articles/update.html";
    }


    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer
    ){
        service.update(id, title, content, writer);
        return String.format("redirect:/articles/%d", id);
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id

    ){
        service.delete(id);
        return "redirect:/articles";
    }












}
