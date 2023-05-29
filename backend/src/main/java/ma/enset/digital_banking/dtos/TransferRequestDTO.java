package ma.enset.digital_banking.dtos;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String compteSource;
    private String compteDestination;
    private double  Montant;
    private String description;
}
