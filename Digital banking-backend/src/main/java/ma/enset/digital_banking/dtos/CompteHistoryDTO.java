package ma.enset.digital_banking.dtos;

import lombok.Data;

import java.util.List;


@Data
public class CompteHistoryDTO {
    private String id_compte;
    private double solde;

    private  int currentPage;
    private  int totalPages;
    private  int pagesize;
    private List<CompteOperationDTO> compteOperationDTOS;

}
