package giuseppelongo.u5d15progetto.services;

import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.exceptions.UnauthorizedException;
import giuseppelongo.u5d15progetto.payloads.UtenteLoginDTO;
import giuseppelongo.u5d15progetto.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UtenteLoginDTO body) {

        Utente found = utenteService.findByUsername(body.username())
                .orElseThrow(() -> new UnauthorizedException("Credenziali errate!"));

        if (bcrypt.matches(body.password(), found.getPassword())) {

            return jwtTools.createToken(found);
        } else {
           
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
