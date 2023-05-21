package ma.enset.digital_banking.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digital_banking.dtos.*;
import ma.enset.digital_banking.entities.*;
import ma.enset.digital_banking.enums.TypeOperation;
import ma.enset.digital_banking.exceptions.BalanceNotSufficientException;
import ma.enset.digital_banking.exceptions.ClientNotFoundException;
import ma.enset.digital_banking.exceptions.CompteBancaireNotFoundException;
import ma.enset.digital_banking.mappers.CompteBancaireMapperImpl;
import ma.enset.digital_banking.repositories.ClientRepository;
import ma.enset.digital_banking.repositories.CompteBancaireRepository;
import ma.enset.digital_banking.repositories.CompteOperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
//journalisation
@Slf4j
public class CompteBancaireServiceImpl implements CompteBancaireService {

    private ClientRepository clientRepository;
    private CompteBancaireRepository compteBancaireRepository;
    private CompteOperationRepository compteOperationRepository;
    private CompteBancaireMapperImpl dtoMapper;
    @Override
    public ClientDTO saveClient(ClientDTO clientDto) {
        log.info("Enregistrement d'un nouvelle client");
        Client client=dtoMapper.fromClientDto(clientDto);
        Client clt=clientRepository.save(client);
        return dtoMapper.fromClient(clt);
    }

    @Override
    public CurrentCompteBancaireDTO saveCurrentCompte(double initialSolde, double decouvert, Long id_clt) {
        Client client=clientRepository.findById(id_clt).orElse(null);
        if(client==null)
            throw new ClientNotFoundException("le client n'existe pas");
        CompteCurrant compteCurrant=new CompteCurrant();
        compteCurrant.setId(UUID.randomUUID().toString());
        compteCurrant.setDateCreation(new Date());
        compteCurrant.setSolde(initialSolde);
        compteCurrant.setClient(client);
        compteCurrant.setDecouvert(decouvert);
        CompteCurrant compteCurrantsave=compteBancaireRepository.save(compteCurrant);
        return dtoMapper.fromCurrentCompteBanque(compteCurrantsave);
    }

    @Override
    public AjoutCompteBancaireDTO saveAjoutCompte(double initialSolde, double tauxInteret, Long id_clt) {
        Client client=clientRepository.findById(id_clt).orElse(null);
        if(client==null)
            throw new ClientNotFoundException("le client n'existe pas");
        AjoutCompte ajoutCompte=new AjoutCompte();
        ajoutCompte.setId(UUID.randomUUID().toString());
        ajoutCompte.setDateCreation(new Date());
        ajoutCompte.setSolde(initialSolde);
        ajoutCompte.setClient(client);
        ajoutCompte.setTauxInteret(tauxInteret);
        AjoutCompte save=compteBancaireRepository.save(ajoutCompte);
        return dtoMapper.fromSavingCompteBanque(save);
    }


    @Override
    public List<ClientDTO> listclients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOS=clients.stream().map(client -> dtoMapper.fromClient(client)).collect(Collectors.toList());
        return clientDTOS;
    }

    @Override
    public CompteBancaireDTO getCompteBancaire(String id_compte) throws CompteBancaireNotFoundException {
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id_compte)
                .orElseThrow(()->new CompteBancaireNotFoundException("le compte n'existe pas"));
        if (compteBancaire instanceof AjoutCompte)
        {
            AjoutCompte compte=(AjoutCompte) compteBancaire;
            return dtoMapper.fromSavingCompteBanque(compte);
        }
        else {
            CompteCurrant compteCurrant=(CompteCurrant) compteBancaire;
            return dtoMapper.fromCurrentCompteBanque(compteCurrant);
        }
    }

    @Override
    public void debit(String id_compte, double montant, String description) throws CompteBancaireNotFoundException, BalanceNotSufficientException {
        //CompteBancaire compteBancaire=getCompteBancaire(id_compte);
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id_compte)
                .orElseThrow(()->new CompteBancaireNotFoundException("le compte n'existe pas"));
        if (compteBancaire.getSolde()<montant)
            throw new BalanceNotSufficientException("le solde n'est pas suffisant");
        CompteOperation compteOperation=new CompteOperation();
        compteOperation.setTypeOperation(TypeOperation.DEBIT);
        compteOperation.setMontant(montant);
        compteOperation.setDateOperation(new Date());
        compteOperation.setDescription(description);
        compteOperation.setCompteBancaire(compteBancaire);
        compteOperationRepository.save(compteOperation);
        //mise à jour
        compteBancaire.setSolde(compteBancaire.getSolde()-montant);
        compteBancaireRepository.save(compteBancaire);

    }

    @Override
    public void credit(String id_compte, double montant, String description) throws CompteBancaireNotFoundException {
        //CompteBancaire compteBancaire=getCompteBancaire(id_compte);
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id_compte)
                .orElseThrow(()->new CompteBancaireNotFoundException("le compte n'existe pas"));
        CompteOperation compteOperation=new CompteOperation();
        compteOperation.setTypeOperation(TypeOperation.CREDIT);
        compteOperation.setMontant(montant);
        compteOperation.setDateOperation(new Date());
        compteOperation.setDescription(description);
        compteOperation.setCompteBancaire(compteBancaire);
        compteOperationRepository.save(compteOperation);
        //mise à jour
        compteBancaire.setSolde(compteBancaire.getSolde()+ montant);
        compteBancaireRepository.save(compteBancaire);
    }

    @Override
    public void transfer(String id_compteSource, String id_compteDestination, double montant) throws CompteBancaireNotFoundException, BalanceNotSufficientException {
    debit(id_compteSource,montant,"Transfere to "+id_compteDestination);
    credit(id_compteDestination,montant,"Transfere from "+id_compteSource);
    }

    @Override
    public List<CompteBancaireDTO> compteBancaireList(){
        List<CompteBancaire> compteBancaires =compteBancaireRepository.findAll();
        List<CompteBancaireDTO> bancaireDTOS=compteBancaires.stream().map(compte->{
            if(compte instanceof AjoutCompte){
                AjoutCompte ajoutcompte=(AjoutCompte) compte;
                return dtoMapper.fromSavingCompteBanque(ajoutcompte);
            }
            else {
                CompteCurrant compteCurrant=(CompteCurrant) compte;
                return dtoMapper.fromCurrentCompteBanque(compteCurrant);

            }
        }).collect(Collectors.toList());
       return bancaireDTOS;
    }

    @Override
    public ClientDTO getClient(Long id_client){
        Client clt=clientRepository.findById(id_client)
                .orElseThrow(()->new ClientNotFoundException("client n'existe pas"));
        return dtoMapper.fromClient(clt);
    }
    @Override
    public ClientDTO updateClient(ClientDTO clientDto) {
        log.info("Enregistrement d'un nouvelle client");
        Client client=dtoMapper.fromClientDto(clientDto);
        Client clt=clientRepository.save(client);
        return dtoMapper.fromClient(clt);
    }

    @Override
    public  void deleteClient(Long id_client){
        clientRepository.deleteById(id_client);
    }

    @Override
    public  List<CompteOperationDTO> historiqueCompte(String id_compte){
        List<CompteOperation> compteOperations=compteOperationRepository.findByCompteBancaireId(id_compte);
        return compteOperations.stream().map(op->dtoMapper.fromOperation(op)).collect(Collectors.toList());
    }

    @Override
    public CompteHistoryDTO getComptesHistory(String idCompte, int page, int size) throws CompteBancaireNotFoundException {
        CompteBancaire compteBancaire=compteBancaireRepository.findById(idCompte).orElse(null);
        if (compteBancaire==null) throw new CompteBancaireNotFoundException(" compte n'exite pas ");
        Page<CompteOperation> compteOperationPage=compteOperationRepository.findByCompteBancaireId(idCompte, PageRequest.of(page, size));
        CompteHistoryDTO compteHistoryDTO=new CompteHistoryDTO();
        List<CompteOperationDTO> compteOperationsDTO = compteOperationPage.getContent().stream().map(
                op -> dtoMapper.fromOperation(op)).collect(Collectors.toList());
        compteHistoryDTO.setCompteOperationDTOS(compteOperationsDTO);
        compteHistoryDTO.setId_compte(compteBancaire.getId());
        compteHistoryDTO.setSolde(compteBancaire.getSolde());
        compteHistoryDTO.setCurrentPage(page);
        compteHistoryDTO.setPagesize(size);
        compteHistoryDTO.setTotalPages(compteOperationPage.getTotalPages());
        return compteHistoryDTO;
    }


}
