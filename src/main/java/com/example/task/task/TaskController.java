package com.example.task.task;

import com.example.task.exception.BadRequestException;
import com.example.task.payloads.entities.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.method.P;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/{year}")
    public Task save(@PathVariable String year,@RequestBody @Validated TaskDTO taskDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return taskService.save(taskDTO,year);
    }

    @GetMapping("")
        public List<Task> findAll(){
            return taskService.getAll();
        }
        @GetMapping("/{id}")
    public Task findByIf(@PathVariable long id){
        return taskService.getById(id);
        }
                @PutMapping("/{id}/{year}")
    public Task putById(@PathVariable long id,@PathVariable String year, @RequestBody TaskDTO taskDTO){
        return taskService.updateById(id,taskDTO,year);
                }


                @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id){
        return taskService.deleteById(id);
                }

                @GetMapping("/paginated")
    public Page<Task> getAllPaginated(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id") String orderBy){
        return taskService.getAllPaginated(page,size,orderBy);
                }

                @GetMapping("/meseAndYear/{mese}/{year}")
    public List<Task> getByMeseAndYear(@PathVariable long mese, @PathVariable String year){
        return taskService.findByMeseIdAndYear(mese,year);
                }

    @GetMapping("/meseAndYearAndUser/{user}/{mese}/{year}")
    public List<Task> getByMeseAndYearAndUser(@PathVariable long user,@PathVariable long mese, @PathVariable String year){
        return taskService.getAllByUserIdAndMeseIdAndYear(user,mese,year);
    }

    @GetMapping("/UserAndMeseAndDateAndNomeMese/{user}/{mese}/{year}/{nome}")
    public List<Task> getAllByMeseUserYearAndNome(@PathVariable long user,@PathVariable long mese, @PathVariable String year, @PathVariable String nome){
        return taskService.findByNomeAndMeseAndUser(nome,mese,user,year);
    }
}
