package ma.enset.digital_banking.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digital_banking.entities.CompteBancaire;
import ma.enset.digital_banking.enums.TypeOperation;

import java.util.Date;


@Data
public class CompteOperationDTO {

    private Long id;
    private Date dateOperation;
    private  double montant;

    private TypeOperation typeOperation;
    private  String description;

}
