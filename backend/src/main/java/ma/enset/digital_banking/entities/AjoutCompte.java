package ma.enset.digital_banking.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//heritage
//1ere type :
@DiscriminatorValue("AC")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AjoutCompte extends CompteBancaire{
   private  double tauxInteret;

}
