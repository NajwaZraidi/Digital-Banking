package ma.enset.digital_banking.dtos;

import lombok.Data;

@Data
public class DebitDTO {
    private String id_compte;
    private double montant;
    private String description;
}
