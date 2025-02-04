// src/app/dto/client.dto.ts
export interface ClientDTO {
    id: number;
    name: string;
    identification: string;
    age: string;
    gender: string;
    phone: string;
    password: "string";
    status: "string";
    clientReference: 33;
    createAt?: Date;
    updateAt?: Date;
  }
  
  // Optional: Create a DTO for creating/updating clients
  export interface CreateClientDTO {
    name: string;
    apellido: string;
    email: string;
  }
  
  export interface UpdateClientDTO extends Partial<CreateClientDTO> {
    id: number;
  }