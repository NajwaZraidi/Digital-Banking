package ma.enset.digital_banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digital_banking.enums.TypeOperation;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CompteOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date dateOperation;
    private  double montant;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    private  String description;
    @ManyToOne
    private  CompteBancaire compteBancaire;
}
