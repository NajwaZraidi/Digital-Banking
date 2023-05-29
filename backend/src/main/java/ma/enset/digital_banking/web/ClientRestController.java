package ma.enset.digital_banking.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digital_banking.dtos.ClientDTO;
import ma.enset.digital_banking.dtos.CompteBancaireDTO;
import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.services.CompteBancaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
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

    @GetMapping("/clients/recherche")
    public List<ClientDTO> recherche(@RequestParam(name = "motCle",defaultValue = "") String motCle ){
        return compteBancaireService.rechercher(motCle);
    }

    @GetMapping("/client/{id}/comptes")
    public List<CompteBancaireDTO> comptesListOfClient(@PathVariable(name = "id") Long id_client) {
        return compteBancaireService.comptesListOfClient(id_client);
    }
}
