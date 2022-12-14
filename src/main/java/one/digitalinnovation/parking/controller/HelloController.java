package one.digitalinnovation.parking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class HelloController {

    // Somente para iniciar a API com unico metodo com mensagem de boas vindas.
    @GetMapping
    public String hello() {
        return "Hello World!";
    }

}
