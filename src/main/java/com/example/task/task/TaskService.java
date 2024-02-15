package com.example.task.task;

import com.example.task.mese.Mese;
import com.example.task.mese.MeseRepository;
import com.example.task.payloads.entities.TaskDTO;
import com.example.task.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class TaskService {
@Autowired
    TaskRepository taskRepository;
@Autowired
    MeseRepository meseRepository;
@Autowired
    UserRepository userRepository;
    public List<Task> getAllByMeseId(long mese_id){
       return taskRepository.findAllByMese_Id(mese_id);
    }
    public List<Task> getAllByUserId(long user_id){
        return taskRepository.findAllByUser_Id(user_id);
    }
    public List<Task> getAllByUserIdAndMeseId(long user_id,long mese_id){
        return taskRepository.findAllByUser_IdAndMese_id(user_id,mese_id);
    }

    public Task save(TaskDTO taskDTO){
        Task task = new Task();
        task.setData(LocalDate.of(Year.now().getValue(), taskDTO.mese(), taskDTO.giorno()));
task.setMese(meseRepository.findById(taskDTO.mese_id()).get());
task.setTesto(taskDTO.testo());
task.setUser(userRepository.findById(taskDTO.user_id()).get());
task.setOra(taskDTO.ora());
return taskRepository.save(task);
    }

    public boolean deleteById(long task_id){
        try {
            taskRepository.deleteById(task_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Task updateById(long task_id,TaskDTO taskDTO){
        Task task = taskRepository.findById(task_id).get();
        task.setData(LocalDate.of(Year.now().getValue(), taskDTO.mese(), taskDTO.giorno()));
        task.setMese(meseRepository.findById(taskDTO.mese_id()).get());
        task.setTesto(taskDTO.testo());
        task.setUser(userRepository.findById(taskDTO.user_id()).get());
        task.setOra(taskDTO.ora());
        return taskRepository.save(task);
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public Page<Task> getAllPaginated(int page ,int size , String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return taskRepository.findAll(pageable);
    }

}
