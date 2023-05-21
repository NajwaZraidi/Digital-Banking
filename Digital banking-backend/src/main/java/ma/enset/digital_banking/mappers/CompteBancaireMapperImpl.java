package ma.enset.digital_banking.mappers;

import ma.enset.digital_banking.dtos.ClientDTO;
import ma.enset.digital_banking.entities.Client;
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
}
