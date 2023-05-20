package ma.enset.digital_banking.repositories;

import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.entities.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,String> {
}
