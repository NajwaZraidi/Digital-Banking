package ma.enset.digital_banking.repositories;

import ma.enset.digital_banking.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
 List<Client> findByNameContains(String motCle);
}
