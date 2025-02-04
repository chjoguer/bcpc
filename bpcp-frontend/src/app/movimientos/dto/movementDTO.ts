export interface movementDTO {
    id: number;
    status: "string";
    numberAccount: "string";
    typeMovement: "string";
    initialAmount: "Double";
    movementAmount: "Double";
    createAt?: Date;
    updateAt?: Date;
    
  }