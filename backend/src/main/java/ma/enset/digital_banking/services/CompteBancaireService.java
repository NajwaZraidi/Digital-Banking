package ma.enset.digital_banking.services;

import ma.enset.digital_banking.dtos.*;
import ma.enset.digital_banking.entities.AjoutCompte;
import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.entities.CompteBancaire;
import ma.enset.digital_banking.entities.CompteCurrant;
import ma.enset.digital_banking.exceptions.BalanceNotSufficientException;
import ma.enset.digital_banking.exceptions.CompteBancaireNotFoundException;

import java.util.List;

public interface CompteBancaireService {

    ClientDTO saveClient(ClientDTO clientDto);

    CurrentCompteBancaireDTO saveCurrentCompte(double initialSolde, double decouvert, Long id_clt);
    AjoutCompteBancaireDTO saveAjoutCompte(double initialSolde, double tauxInteret, Long id_clt);
    List<ClientDTO> listclients();

    CompteBancaireDTO getCompteBancaire(String id_compte) throws CompteBancaireNotFoundException;

    // creation des operation credit et debit

    void debit(String id_compte,double montant,String description) throws CompteBancaireNotFoundException, BalanceNotSufficientException;
    void credit(String id_compte,double montant,String description) throws CompteBancaireNotFoundException;
    void transfer(String id_compteSource,String id_compteDestination,double montant) throws CompteBancaireNotFoundException, BalanceNotSufficientException;

    List<CompteBancaireDTO> compteBancaireList();

    ClientDTO getClient(Long id_client);

    ClientDTO updateClient(ClientDTO clientDto);

    void deleteClient(Long id_client);

    List<CompteOperationDTO> historiqueCompte(String id_compte);

    CompteHistoryDTO getComptesHistory(String idCompte, int page, int size) throws CompteBancaireNotFoundException;

    List<ClientDTO> rechercher(String motCle);

}
