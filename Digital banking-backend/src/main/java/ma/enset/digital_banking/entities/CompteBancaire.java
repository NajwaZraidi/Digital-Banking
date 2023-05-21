package ma.enset.digital_banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digital_banking.enums.StatusCompte;

import java.util.Date;
import java.util.List;
@Entity
//heritage
//1ere type :
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 2eme type heritage :@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//3eme type :
//@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE",length = 4)
@Data @NoArgsConstructor
@AllArgsConstructor
// 2eme type heritage : public abstract class CompteBancaire {
public  class CompteBancaire{
    @Id
    private String id;
    private double solde;
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    private StatusCompte status;
     @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "compteBancaire",fetch = FetchType.LAZY)
    private List<CompteOperation> compteOperations;

}
