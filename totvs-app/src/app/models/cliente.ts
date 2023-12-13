export interface Cliente {
  id?: number;
  nome: string;
  cpf?: string;
  endereco?: string;
  bairro?: string;
  telefones?: Telefone[];
}

export interface Telefone {
  numero: string;
}
