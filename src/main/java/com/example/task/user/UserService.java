package com.example.task.user;

import com.example.task.calendario.Calendario;
import com.example.task.exception.NotFoundException;
import com.example.task.payloads.entities.UserRegistrationDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository utenteRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteCalendario(User user) {

        Query deleteUsersQuery = entityManager.createNativeQuery("DELETE FROM calendario_user WHERE user_id = ?");
        deleteUsersQuery.setParameter(1, user.getId());
        deleteUsersQuery.executeUpdate();

        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
    @Transactional
    public boolean findByIdAndDelete(long id) throws NotFoundException {
        try{
            User found = this.findById(id);
            deleteCalendario(found);
            utenteRepository.deleteById(found.getId());
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User findById(long id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(long id, UserRegistrationDTO body) throws NotFoundException {
        User found = utenteRepository.findById(id).get();
        found.setNome(body.nome());
        found.setEmail(body.email());
        found.setCognome(body.cognome());
        found.setEta(body.eta());
        return utenteRepository.save(found);
    }



    public User findByEmail(String email) throws NotFoundException {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email "+ email + " non trovato"));
    }
}
