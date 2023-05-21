package ma.enset.digital_banking;

import ma.enset.digital_banking.entities.*;
import ma.enset.digital_banking.enums.StatusCompte;
import ma.enset.digital_banking.enums.TypeOperation;
import ma.enset.digital_banking.exceptions.BalanceNotSufficientException;
import ma.enset.digital_banking.exceptions.CompteBancaireNotFoundException;
import ma.enset.digital_banking.repositories.ClientRepository;
import ma.enset.digital_banking.repositories.CompteBancaireRepository;
import ma.enset.digital_banking.repositories.CompteOperationRepository;
import ma.enset.digital_banking.services.BanqueService;
import ma.enset.digital_banking.services.CompteBancaireService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CompteBancaireService compteBancaireService){
        return args -> {
            Stream.of("Islam","Hania","Khalid","Abdelkbir").forEach(
                    name->{
                        Client client=new Client();
                        client.setName(name);
                        client.setEmail(name+"@gmail.com");
                        compteBancaireService.saveClient(client);
                    });
            compteBancaireService.listclients().forEach(client -> {
                compteBancaireService.saveCurrentCompte(Math.random()*900000,9000,client.getId());
                compteBancaireService.saveAjoutCompte(Math.random()*1200000,5.5,client.getId());
                List<CompteBancaire> compteBancaireList=compteBancaireService.compteBancaireList();
                for (CompteBancaire compte:compteBancaireList) {
                    for (int i=0;i<10;i++){
                        try {
                            compteBancaireService.credit(compte.getId(),10000+Math.random()*120000,"Credit");
                            compteBancaireService.debit(compte.getId(),10000+Math.random()*900,"Debit");

                        } catch (CompteBancaireNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (BalanceNotSufficientException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            });
           // banqueService.consulter();
        };
    }
    //@Bean
    CommandLineRunner strat(ClientRepository clientRepository, CompteBancaireRepository compteBancaireRepository
                             , CompteOperationRepository compteOperationRepository)
    {

       return args -> {

           Stream.of("Hassan","Najwa","Awjan","Islam").forEach(
                   name->{
                       Client client=new Client();
                       client.setName(name);
                       client.setEmail(name+"@gmail.com");
                       clientRepository.save(client);
                   });
           // chaque client à deux compte
           clientRepository.findAll().forEach(clt->{
               CompteCurrant compteCurrant=new CompteCurrant();
               compteCurrant.setId(UUID.randomUUID().toString());
               compteCurrant.setSolde(Math.random()*90000);
               compteCurrant.setDateCreation(new Date());
               compteCurrant.setStatus(Math.random()>0.5? StatusCompte.CREE : StatusCompte.ACTIVER);
               compteCurrant.setClient(clt);
               compteCurrant.setDecouvert(9000);
               compteBancaireRepository.save(compteCurrant);

               AjoutCompte ajoutCompte=new AjoutCompte();
               ajoutCompte.setId(UUID.randomUUID().toString());
               ajoutCompte.setSolde(Math.random()*90000);
               ajoutCompte.setDateCreation(new Date());
               ajoutCompte.setStatus(StatusCompte.CREE);
               ajoutCompte.setClient(clt);
               ajoutCompte.setTauxInteret(5.5);
               compteBancaireRepository.save(ajoutCompte);
           });

           // chaque compte à 10 operation

           compteBancaireRepository.findAll().forEach(
                   compte->{

                       for (int i=0;i<10;i++){
                           CompteOperation compteOperation=new CompteOperation();
                           compteOperation.setDateOperation(new Date());
                           compteOperation.setMontant(Math.random()*12000);
                           compteOperation.setTypeOperation(Math.random()>0.5? TypeOperation.DEBIT :TypeOperation.CREDIT );
                           compteOperation.setCompteBancaire(compte);
                           compteOperationRepository.save(compteOperation);
                       }
                   });
       };
    }
}
