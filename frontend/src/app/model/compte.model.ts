export interface CompteDeatails {
  id_compte:           string;
  solde:               number;
  currentPage:         number;
  totalPages:          number;
  pagesize:            number;
  compteOperationDTOS: CompteOperation[];
}

export interface CompteOperation{
  id:            number;
  dateOperation: Date;
  montant:       number;
  typeOperation: string;
  description:   string;
}
