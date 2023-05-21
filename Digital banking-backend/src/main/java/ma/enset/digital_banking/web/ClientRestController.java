package ma.enset.digital_banking.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digital_banking.dtos.ClientDTO;
import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.services.CompteBancaireService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable(name = "id") Long id_client){
        return compteBancaireService.getClient(id_client);
    }

    @PostMapping("/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO request){
     return compteBancaireService.saveClient(request);
    }

    @PutMapping("/clients/{id_client}")
    public ClientDTO updateClient(@PathVariable Long id_client,@RequestBody ClientDTO clientDTO){
        clientDTO.setId(id_client);
        return compteBancaireService.updateClient(clientDTO);
    }

    @DeleteMapping("/clients/{id_client}")
    public void deleteClient(@PathVariable Long id_client){
        compteBancaireService.deleteClient(id_client);
    }
}
