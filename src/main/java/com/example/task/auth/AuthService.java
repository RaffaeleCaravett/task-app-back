package com.example.task.auth;



import com.example.task.calendario.Calendario;
import com.example.task.calendario.CalendarioRepository;
import com.example.task.calendario.CalendarioService;
import com.example.task.enums.Role;
import com.example.task.enums.TipoAnno;
import com.example.task.exception.BadRequestException;
import com.example.task.exception.UnauthorizedException;
import com.example.task.mese.Mese;
import com.example.task.mese.MeseRepository;
import com.example.task.payloads.entities.Token;
import com.example.task.payloads.entities.UserLoginDTO;
import com.example.task.payloads.entities.UserRegistrationDTO;
import com.example.task.security.JWTTools;
import com.example.task.user.User;
import com.example.task.user.UserRepository;
import com.example.task.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    CalendarioService calendarioService;

    @Autowired
    MeseRepository meseRepository;
    public Token authenticateUser(UserLoginDTO body) throws Exception {
        // 1. Verifichiamo che l'email dell'utente sia nel db
        User user = usersService.findByEmail(body.email());
        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(bcrypt.matches(body.password(), user.getPassword()))  {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }


    public User registerUser(UserRegistrationDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        userRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        User newUser = new User();
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setEta(body.eta());
        newUser.setRole(Role.USER);
        newUser.setCalendarioList(new ArrayList<>());


        LocalDate localDate = LocalDate.now();
        Calendario calendario= new Calendario();
        calendario.setTipoAnno(TipoAnno.NORMALE);
        if(localDate.getYear()%4==0||localDate.getYear()%100==0&&localDate.getYear()%400==0){
                    calendario.setTipoAnno(TipoAnno.BISESTILE);
        }
        List<Mese> meseList = meseRepository.findAll();
        calendario.setMeseList(meseList);
        calendario.setAnno(localDate.getYear());
        calendario.setUserList(new ArrayList<>());
        calendario.getUserList().add(newUser);
        newUser.getCalendarioList().add(calendarioService.saveCalendario(calendario));


        userRepository.save(newUser);

        return newUser;
    }
    public Page<User> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return userRepository.findAll(pageable);
    }

}