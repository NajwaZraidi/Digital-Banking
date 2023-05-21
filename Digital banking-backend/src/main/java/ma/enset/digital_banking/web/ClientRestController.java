package ma.enset.digital_banking.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digital_banking.dtos.ClientDTO;
import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.services.CompteBancaireService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
//@RequestMapping("/Clients")
public class ClientRestController {
    private CompteBancaireService compteBancaireService;

    @GetMapping("/clients")
    public List<ClientDTO> clients(){
        return compteBancaireService.listclients();
    }
}
