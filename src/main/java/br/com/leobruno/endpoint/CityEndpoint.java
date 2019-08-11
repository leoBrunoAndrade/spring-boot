package br.com.leobruno.endpoint;

import br.com.leobruno.model.City;
import br.com.leobruno.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/cities")
public class CityEndpoint {

    @Autowired
    private CityService cityServiceInt;


    @PostMapping
    public ResponseEntity<?> save (@Valid @RequestBody City city){
        return cityServiceInt.save(city);
    }

    @GetMapping (path = "/name/{name}")
    public  ResponseEntity<?> findByName(@PathVariable("name") String name){
        return cityServiceInt.findByName(name);
    }

    @GetMapping (path = "/state/{state}")
    public  ResponseEntity<?> findByState(@PathVariable("state") String state){
        return cityServiceInt.findByState(state);
    }


}
