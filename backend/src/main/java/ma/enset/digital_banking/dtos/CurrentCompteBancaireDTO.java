package ma.enset.digital_banking.dtos;


import lombok.Data;
import ma.enset.digital_banking.enums.StatusCompte;

import java.util.Date;


@Data
public  class CurrentCompteBancaireDTO extends CompteBancaireDTO {
    private String id;
    private double solde;
    private Date dateCreation;
    private StatusCompte status;
    private ClientDTO clientDTO;
    private double decouvert;

}
