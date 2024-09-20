package giuseppelongo.u5d15progetto.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    UTENTE_NORMALE,
    ORGANIZZATORE_EVENTI;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}
