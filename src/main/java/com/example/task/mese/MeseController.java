package com.example.task.mese;

import com.example.task.calendario.Calendario;
import com.example.task.exception.BadRequestException;
import com.example.task.payloads.entities.CalendarioDTO;
import com.example.task.payloads.entities.MeseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mese")
public class MeseController {

    @Autowired
    MeseService meseService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mese save(@RequestBody @Validated MeseDTO meseDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return  meseService.save(meseDTO);
    }

    @GetMapping("")
    public List<Mese> getAll(){
        return meseService.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mese putById(@PathVariable long id, @RequestBody MeseDTO meseDTO){
        return meseService.putById(id,meseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable long id){
        return  meseService.deleteById(id);
    }
    @GetMapping("/{id}")
    public Mese getById(@PathVariable long id){
        return meseService.getById(id);
    }

    @GetMapping("/noTasks/{year}")
    public List<Mese> getWithNoTasks(@PathVariable int year){
        return meseService.getWithNoTasks(year);
    }

}
