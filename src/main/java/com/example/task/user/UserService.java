package com.example.task.user;

import com.example.task.exception.NotFoundException;
import com.example.task.payloads.entities.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository utenteRepository;


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

    public void findByIdAndDelete(long id) throws NotFoundException {
        User found = this.findById(id);
        utenteRepository.delete(found);
    }

    public User findByEmail(String email) throws NotFoundException {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email "+ email + " non trovato"));
    }
}
