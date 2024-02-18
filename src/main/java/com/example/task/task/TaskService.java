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
    public List<Task> findByMeseIdAndYear(long meseId, String year) {
        LocalDate startDate = LocalDate.parse(year + "-01-01");
        LocalDate endDate = LocalDate.parse(year + "-12-31");
        return taskRepository.findAllByMese_IdAndDataBetween(meseId, startDate, endDate);
    }
    public List<Task> getAllByUserId(long user_id){
        return taskRepository.findAllByUser_Id(user_id);
    }
    public List<Task> getAllByUserIdAndMeseIdAndYear(long user_id,long mese_id,String year) {
        LocalDate startDate = LocalDate.parse(year + "-01-01");
        LocalDate endDate = LocalDate.parse(year + "-12-31");
        return taskRepository.findAllByUser_IdAndMese_idAndDataBetween(user_id,mese_id, startDate, endDate);
    }

    public Task save(TaskDTO taskDTO){
        Task task = new Task();
        task.setData(LocalDate.of(Year.now().getValue(), taskDTO.mese(), taskDTO.giornoDellaSettimana()));
task.setMese(meseRepository.findById(taskDTO.mese_id()).get());
task.setTesto(taskDTO.testo());
task.setUser(userRepository.findById(taskDTO.user_id()).get());
task.setGiornoDelMese(taskDTO.giornoDelMese());
task.setGiornoDellaSettimana(taskDTO.giornoDellaSettimana());
task.setGiornoDellaSettimanaNome(taskDTO.giornoDellaSettimanaNome());
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
        task.setData(LocalDate.of(Year.now().getValue(), taskDTO.mese(), taskDTO.giornoDellaSettimana()));
        task.setMese(meseRepository.findById(taskDTO.mese_id()).get());
        task.setTesto(taskDTO.testo());
        task.setUser(userRepository.findById(taskDTO.user_id()).get());
        task.setGiornoDelMese(taskDTO.giornoDelMese());
        task.setGiornoDellaSettimana(taskDTO.giornoDellaSettimana());
        task.setGiornoDellaSettimanaNome(taskDTO.giornoDellaSettimanaNome());
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
public Task getById(long task_id){
        return  taskRepository.findById(task_id).get();
}
}
