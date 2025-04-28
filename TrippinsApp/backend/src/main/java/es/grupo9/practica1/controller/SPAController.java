package main.java.es.grupo9.practica1.controller;

@Controller

public class SPAController {

    @GetMapping({"/spa", "/spa/**/{path:[^\\.]*}", "/{path:spa[^\\.]*}"})

    public String redirect() {

        return "forward:/spa/index.html";

    }


}