package ma.enset.digital_banking.mappers;

import ma.enset.digital_banking.dtos.AjoutCompteBancaireDTO;
import ma.enset.digital_banking.dtos.ClientDTO;
import ma.enset.digital_banking.dtos.CompteOperationDTO;
import ma.enset.digital_banking.dtos.CurrentCompteBancaireDTO;
import ma.enset.digital_banking.entities.AjoutCompte;
import ma.enset.digital_banking.entities.Client;
import ma.enset.digital_banking.entities.CompteCurrant;
import ma.enset.digital_banking.entities.CompteOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
//MapStruct
@Service
public class CompteBancaireMapperImpl {

    public ClientDTO fromClient(Client client){
        ClientDTO clientDTO=new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
//        clientDTO.setId(client.getId());
//        clientDTO.setName(client.getName());
//        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

    public Client fromClientDto(ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;
    }

    // ajout : compte =>comptedto
    public AjoutCompteBancaireDTO fromSavingCompteBanque(AjoutCompte ajoutCompte){
        AjoutCompteBancaireDTO compteBancaireDTO=new AjoutCompteBancaireDTO();
        BeanUtils.copyProperties(ajoutCompte,compteBancaireDTO);
        //client
        compteBancaireDTO.setClientDTO(fromClient(ajoutCompte.getClient()));
        compteBancaireDTO.setType(ajoutCompte.getClass().getName());
        return compteBancaireDTO;
    }
    // ajout : comptedto =>compte
    public AjoutCompte fromSavingCompteBanqueDto(AjoutCompteBancaireDTO compteBancaireDTO){
        AjoutCompte compteBancaire=new AjoutCompte();
        BeanUtils.copyProperties(compteBancaireDTO,compteBancaire);
        //client
        compteBancaire.setClient(fromClientDto(compteBancaireDTO.getClientDTO()));
        compteBancaireDTO.setClientDTO(fromClient(compteBancaire.getClient()));
        return compteBancaire;
    }

    // current : compte =>comptedto
    public CurrentCompteBancaireDTO fromCurrentCompteBanque(CompteCurrant compteCurrant){
        CurrentCompteBancaireDTO compteBancaireDTO=new CurrentCompteBancaireDTO();
        BeanUtils.copyProperties(compteCurrant,compteBancaireDTO);
        //client
        compteBancaireDTO.setClientDTO(fromClient(compteCurrant.getClient()));
        compteBancaireDTO.setType(compteCurrant.getClass().getName());
        return compteBancaireDTO;
    }
    // current : comptedto =>compte
    public CompteCurrant fromCurrentCompteBanqueDto(CurrentCompteBancaireDTO compteBancaireDTO){
        CompteCurrant compteBancaire=new CompteCurrant();
        BeanUtils.copyProperties(compteBancaireDTO,compteBancaire);
        //client
        compteBancaire.setClient(fromClientDto(compteBancaireDTO.getClientDTO()));

        return compteBancaire;
    }


    public CompteOperationDTO fromOperation(CompteOperation compteOperation){
        CompteOperationDTO compteOperationDTO=new CompteOperationDTO();
        BeanUtils.copyProperties(compteOperation,compteOperationDTO);
        return compteOperationDTO;
    }



}
