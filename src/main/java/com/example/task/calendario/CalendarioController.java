package com.example.task.calendario;

import com.example.task.exception.BadRequestException;
import com.example.task.mese.MeseService;
import com.example.task.payloads.entities.CalendarioDTO;
import com.example.task.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class CalendarioController {

    @Autowired
    CalendarioService calendarioService;
    @Autowired
    UserService userService;
    @Autowired
    MeseService meseService;


    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Calendario save(@RequestBody @Validated CalendarioDTO calendarioDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return  calendarioService.save(calendarioDTO);
    }

    @GetMapping("")
    public List<Calendario> getAll(){
        return calendarioService.getAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
public Calendario putById(@PathVariable long id, @RequestBody CalendarioDTO calendarioDTO){
        return calendarioService.modifyCalendario(id,calendarioDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
public boolean deleteById(@PathVariable long id){
        return  calendarioService.deletebyId(id);
    }

    @GetMapping("/anno/{anno}")
    public Calendario findByAnno(@PathVariable int anno){
        return  calendarioService.findByAnno(anno);
    }
}
