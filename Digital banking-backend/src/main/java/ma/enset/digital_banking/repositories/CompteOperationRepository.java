package ma.enset.digital_banking.repositories;

import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.entities.CompteOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteOperationRepository extends JpaRepository<CompteOperation,Long> {
     List<CompteOperation> findByCompteBancaireId(String id_compte);
     Page<CompteOperation> findByCompteBancaireId(String id_compte, Pageable pageable);
}
