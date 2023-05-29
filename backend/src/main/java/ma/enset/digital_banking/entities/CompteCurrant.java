package ma.enset.digital_banking.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//heritage CC => compte courant
//1ere type :
@DiscriminatorValue("CC")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CompteCurrant extends CompteBancaire{

    private double decouvert;
}
