package ma.enset.digital_banking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.digital_banking.entities.CompteBancaire;

import java.util.List;


@Data
public class ClientDTO {
     private Long id;
    private  String name;
    private  String email;
}
