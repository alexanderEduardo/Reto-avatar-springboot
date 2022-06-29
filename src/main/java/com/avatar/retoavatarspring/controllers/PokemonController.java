package com.avatar.retoavatarspring.controllers;


import com.avatar.retoavatarspring.models.Pokemon;
import com.avatar.retoavatarspring.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/avatar-reto")
public class PokemonController {

    private final RestTemplate restTemplate;

    @Autowired
    public PokemonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hi")
    public Result getApi(){
        String url="https://jsonplaceholder.typicode.com/todos/1";
        Result result=restTemplate.getForObject(url, Result.class);
        System.out.println(result);
        return result;
    }

    @GetMapping("/all")
    public ResponseEntity<Result[]> getAllResult(){
        String url="https://jsonplaceholder.typicode.com/todos";
        //Result[] listaRes=restTemplate.getForObject(url, Result[].class);
        ResponseEntity<Result[]> response=restTemplate.getForEntity(url, Result[].class);
        System.out.println(Arrays.toString(response.getBody()));
        System.out.println(response.getHeaders().getContentType());
        System.out.println(response.getStatusCode());
        //List<Result> results=new ArrayList<>(Arrays.asList(listaRes));
        //ResponseEntity(T body, HttpStatus status)
        return response;
    }
    
    //abilities
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<?> getPokemons(@PathVariable Long id){
        String url = "https://pokeapi.co/api/v2/pokemon/"+id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent","Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Pokemon> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Pokemon.class);
        }catch (HttpStatusCodeException e){
            Map<String,Object> response_error=new HashMap<>();  
            response_error.put("message","Error ese pokemon no existe en la Api");
            response_error.put("error",e.getMessage());
            //System.out.println(e.getCause().getMessage());
            //ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
            //                    .body(e.getResponseBodyAsString())
            return new ResponseEntity<>(response_error,HttpStatus.NOT_FOUND);
        }
        Pokemon pokemon = response.getBody();
        System.out.println(pokemon); // object : Pokemon
        System.out.println(response.getStatusCode());
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }
}
