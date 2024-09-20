package giuseppelongo.u5d15progetto.controllers;

import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.exceptions.BadRequestException;
import giuseppelongo.u5d15progetto.payloads.NewUtenteDTO;
import giuseppelongo.u5d15progetto.payloads.NewUtenteRespDTO;
import giuseppelongo.u5d15progetto.payloads.UtenteLoginDTO;
import giuseppelongo.u5d15progetto.payloads.UtenteLoginRespDTO;
import giuseppelongo.u5d15progetto.services.AuthService;
import giuseppelongo.u5d15progetto.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload) {
        String token = this.authService.checkCredentialsAndGenerateToken(payload);
        return new UtenteLoginRespDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO register(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {

            Utente savedUtente = utenteService.save(body);
            return new NewUtenteRespDTO(savedUtente.getId());
        }
    }
}
