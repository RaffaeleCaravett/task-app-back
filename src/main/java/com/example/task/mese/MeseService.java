package com.example.task.mese;

import com.example.task.calendario.Calendario;
import com.example.task.calendario.CalendarioService;
import com.example.task.enums.NomeMese;
import com.example.task.payloads.entities.MeseDTO;
import com.example.task.task.Task;
import com.example.task.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeseService {
    @Autowired
    MeseRepository meseRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    CalendarioService calendarioService;
    public Mese save(MeseDTO meseDTO){
        Mese mese= new Mese();
        mese.setNomeMese(NomeMese.valueOf(meseDTO.nome_mese()));
        mese.setGiorni(meseDTO.giorni());
        List<Task> taskList=new ArrayList<>();
        for(Long l: meseDTO.task_id()){
            taskList.add(taskService.getById(l));
        }

        mese.setTasks(taskList);

        List<Calendario> calendarioList = new ArrayList<>();
        for(Long l: meseDTO.calendario_id()){
            calendarioList.add(calendarioService.getById(l));
        }
        mese.setCalendarioList(calendarioList);
    return meseRepository.save(mese);
    }

    public Mese getById(long mese_id){
        return meseRepository.findById(mese_id).get();
    }

    public Mese putById(long mese_id,MeseDTO meseDTO){
        Mese mese=meseRepository.findById(mese_id).get();
        mese.setNomeMese(NomeMese.valueOf(meseDTO.nome_mese()));
        mese.setGiorni(meseDTO.giorni());
        List<Task> taskList=new ArrayList<>();
        for(Long l: meseDTO.task_id()){
            taskList.add(taskService.getById(l));
        }

        mese.setTasks(taskList);

        List<Calendario> calendarioList = new ArrayList<>();
        for(Long l: meseDTO.calendario_id()){
            calendarioList.add(calendarioService.getById(l));
        }
        mese.setCalendarioList(calendarioList);
        return meseRepository.save(mese);
    }

    public List<Mese> findAll(){
        return meseRepository.findAll();
    }

    public boolean deleteById(long mese_id){
        try {
            meseRepository.deleteById(mese_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
public List<Mese> getWithNoTasks(int year){
        List<Mese> meseList = new ArrayList<>();
        for(Mese m : findAll()){
            for(Calendario c : m.getCalendarioList()){
                if (c.getAnno()==year&&m.getTasks().isEmpty()){
                    meseList.add(m);
                }
            }
        }
        return meseList;
}
}
