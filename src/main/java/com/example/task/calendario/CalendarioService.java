package com.example.task.calendario;

import com.example.task.enums.TipoAnno;
import com.example.task.mese.Mese;
import com.example.task.mese.MeseRepository;
import com.example.task.payloads.entities.CalendarioDTO;
import com.example.task.task.Task;
import com.example.task.user.User;
import com.example.task.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarioService {

@Autowired
    CalendarioRepository calendarioRepository;
@Autowired
MeseRepository meseRepository;
@Autowired
    UserRepository userRepository;

public Calendario save(CalendarioDTO calendarioDTO){
    Calendario calendario= new Calendario();
    calendario.setAnno(calendarioDTO.anno());
    calendario.setTipoAnno(TipoAnno.valueOf(calendarioDTO.tipoAnno()));
    List<Mese> meseList=new ArrayList<>();
    for(Long l : calendarioDTO.mese_id()){
        meseList.add(meseRepository.findById(l).get());
    }
    calendario.setMeseList(meseList);

    List<User> userList =new ArrayList<>();

    userList.add(userRepository.findById(calendarioDTO.user_id()).get());

    for(Long l : calendarioDTO.user_ids()){
        userList.add(userRepository.findById(l).get());
    }

    calendario.setUserList(userList);
    return calendarioRepository.save(calendario);

}

public boolean deletebyId(long calendario_id){
    try{
        calendarioRepository.deleteById(calendario_id);
        return true;
    }catch (Exception e){
        return false;
    }
}


public Calendario modifyCalendario(long calendario_id,CalendarioDTO calendarioDTO){
    Calendario calendario = calendarioRepository.findById(calendario_id).get();

    calendario.setAnno(calendarioDTO.anno());
    calendario.setTipoAnno(TipoAnno.valueOf(calendarioDTO.tipoAnno()));
    List<Mese> meseList=new ArrayList<>();
    for(Long l : calendarioDTO.mese_id()){
        meseList.add(meseRepository.findById(l).get());
    }
    calendario.setMeseList(meseList);

    List<User> userList =new ArrayList<>();

    userList.add(userRepository.findById(calendarioDTO.user_id()).get());

    for(Long l : calendarioDTO.user_ids()){
        userList.add(userRepository.findById(l).get());
    }

    calendario.setUserList(userList);
    return calendarioRepository.save(calendario);

}

public List<Calendario> getAll(){
    return  calendarioRepository.findAll();
}
    public Calendario findByAnno(int anno) {
        return calendarioRepository.findByAnno(anno);
    }

}
