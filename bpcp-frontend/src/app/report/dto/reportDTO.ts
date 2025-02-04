export interface reportDTO {
    id: number;
    movementAt: "string";
    name: "string";
    status: "string";
    numberAccount: "string";
    typeAccount: "string";
    initialAmount: "Double";
    movementAmount: "Double";
    totalAmount: "Double";

    createAt?: Date;
    updateAt?: Date;
    
  }

