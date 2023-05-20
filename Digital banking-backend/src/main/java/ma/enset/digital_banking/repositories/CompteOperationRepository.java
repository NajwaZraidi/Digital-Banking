package ma.enset.digital_banking.repositories;

import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.entities.CompteOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteOperationRepository extends JpaRepository<CompteOperation,Long> {
}
