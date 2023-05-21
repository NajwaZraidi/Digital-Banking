package ma.enset.digital_banking.services;


import jakarta.transaction.Transactional;
import ma.enset.digital_banking.entities.AjoutCompte;
import ma.enset.digital_banking.entities.CompteBancaire;
import ma.enset.digital_banking.entities.CompteCurrant;
import ma.enset.digital_banking.repositories.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BanqueService {
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;
    public void consulter(){
        CompteBancaire compteBancaire=
                compteBancaireRepository.findById("1c74a877-ffcc-4262-9e2b-281c45906641").orElse(null);
        if (compteBancaire!=null) {
            System.out.println("*********************");
            System.out.println(compteBancaire.getId());
            System.out.println(compteBancaire.getSolde());
            System.out.println(compteBancaire.getStatus());
            System.out.println(compteBancaire.getDateCreation());
            System.out.println(compteBancaire.getClient().getName());
            System.out.println(compteBancaire.getClass().getSimpleName());
            if (compteBancaire instanceof CompteCurrant) {
                System.out.println(((CompteCurrant) compteBancaire).getDecouvert());
            } else if (compteBancaire instanceof AjoutCompte) {
                System.out.println(((AjoutCompte) compteBancaire).getTauxInteret());
            }
            System.out.println("=================================================");
            compteBancaire.getCompteOperations().forEach(op ->
            {
                System.out.println(op.getTypeOperation() + "\t" + op.getMontant() + "\t" + op.getDateOperation());
            });
            System.out.println("=================================================");
        }
    }
}
