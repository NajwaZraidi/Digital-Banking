package ma.enset.digital_banking.repositories;

import ma.enset.digital_banking.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
