package br.com.leobruno.endpoint;

import br.com.leobruno.model.City;
import br.com.leobruno.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(cityServiceInt.save(city), HttpStatus.CREATED);
    }

    @GetMapping (path = "/name/{name}")
    public  ResponseEntity<?> findByName(@PathVariable("name") String name){
        return new ResponseEntity<>(cityServiceInt.findByName(name),HttpStatus.OK);
    }

    @GetMapping (path = "/state/{state}")
    public  ResponseEntity<?> findByState(@PathVariable("state") String state){
        return new ResponseEntity<>(cityServiceInt.findByState(state),HttpStatus.OK);
    }


}
