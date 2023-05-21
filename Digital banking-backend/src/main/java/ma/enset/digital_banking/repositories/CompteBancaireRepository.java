package ma.enset.digital_banking.repositories;

import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.entities.CompteBancaire;
import ma.enset.digital_banking.entities.CompteOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,String> {

}
