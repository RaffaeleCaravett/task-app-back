package com.example.task.task;

import com.example.task.TaskApplication;
import com.example.task.exception.BadRequestException;
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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Task> getAllByUserIdAndMeseIdAndYear(long user_id,long mese_id,String year,int giornoDelMese) {
        LocalDate startDate = LocalDate.parse(year + "-01-01");
        LocalDate endDate = LocalDate.parse(year + "-12-31");
        return taskRepository.findAllByUser_IdAndMese_idAndDataBetweenAndGiornoDelMese(user_id,mese_id, startDate, endDate,giornoDelMese).stream().sorted(Comparator.comparing(Task::getOra))
                .collect(Collectors.toList());
    }

    public Task save(TaskDTO taskDTO,String year){
        LocalDate startDate = LocalDate.parse(year + "-01-01");
        LocalDate endDate = LocalDate.parse(year + "-12-31");
        if(taskRepository.findByMese_idAndUser_IdAndOraAndDataBetween(taskDTO.mese_id(),taskDTO.user_id(),taskDTO.ora(),startDate,endDate).isPresent()){
            throw new BadRequestException("Hai già un task a quest'ora");
        }

        Task task = new Task();
        task.setData(LocalDate.of(Year.now().getValue(), taskDTO.mese(), taskDTO.giornoDelMese()));
task.setMese(meseRepository.findById(taskDTO.mese_id()).get());
task.setTesto(taskDTO.testo());
task.setUser(userRepository.findById(taskDTO.user_id()).get());
task.setGiornoDelMese(taskDTO.giornoDelMese());
task.setGiornoDellaSettimana(taskDTO.giornoDellaSettimana());
task.setGiornoDellaSettimanaNome(taskDTO.giornoDellaSettimanaNome());
task.setOra(taskDTO.ora());
return taskRepository.save(task);
    }

    public boolean deleteById(long task_id) {
        try {
            Optional<Task> optionalTask = taskRepository.findById(task_id);
            if (optionalTask.isPresent()) {
                taskRepository.deleteById(task_id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Task updateById(long task_id,TaskDTO taskDTO,String year){
        Task task = taskRepository.findById(task_id).get();
        LocalDate startDate = LocalDate.parse(year + "-01-01");
        LocalDate endDate = LocalDate.parse(year + "-12-31");
        if(!Objects.equals(task.getOra(), taskDTO.ora())){
            if(taskRepository.findByMese_idAndUser_IdAndOraAndDataBetween(taskDTO.mese_id(),taskDTO.user_id(),taskDTO.ora(),startDate,endDate).isPresent()){
                throw new BadRequestException("Hai già un task a quest'ora");
            }
        }

        task.setData(LocalDate.of(Year.now().getValue(), taskDTO.mese(), taskDTO.giornoDelMese()));
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


    public List<Task> findByNomeAndMeseAndUser(String nome, long mese, long user, String year){
        LocalDate startDate = LocalDate.parse(year + "-01-01");
        LocalDate endDate = LocalDate.parse(year + "-12-31");

       return taskRepository.findAllByUser_IdAndMese_idAndGiornoDellaSettimanaNomeAndDataBetween(user,mese,nome,startDate,endDate);

    }
public Task getById(long task_id){
        return  taskRepository.findById(task_id).get();
}
}
