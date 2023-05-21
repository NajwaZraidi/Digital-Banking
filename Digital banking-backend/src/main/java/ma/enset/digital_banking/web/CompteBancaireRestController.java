package ma.enset.digital_banking.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digital_banking.dtos.CompteBancaireDTO;
import ma.enset.digital_banking.dtos.CompteHistoryDTO;
import ma.enset.digital_banking.dtos.CompteOperationDTO;
import ma.enset.digital_banking.exceptions.CompteBancaireNotFoundException;
import ma.enset.digital_banking.services.CompteBancaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CompteBancaireRestController {
    private CompteBancaireService compteBancaireService;

    @GetMapping("/comptes/{id_compte}")
    public CompteBancaireDTO getCompteBancaire(@PathVariable String id_compte) throws CompteBancaireNotFoundException {
        return compteBancaireService.getCompteBancaire(id_compte);
    }

    @GetMapping("/comptes")
    public  List<CompteBancaireDTO> listComptes(){
        return compteBancaireService.compteBancaireList();
    }

    @GetMapping("/comptes/{id_compte}/operations")
    public  List<CompteOperationDTO> githistory(@PathVariable String id_compte){
        return  compteBancaireService.historiqueCompte(id_compte);
    }

    @GetMapping("/comptes/{id_compte}/pageOperations")
    public CompteHistoryDTO githistory(@PathVariable String id_compte,
                                       @RequestParam(name = "page",defaultValue = "0") int page,
                                       @RequestParam(name = "size",defaultValue = "5") int size) throws CompteBancaireNotFoundException {
        return  compteBancaireService.getComptesHistory(id_compte,page,size);
    }

}
