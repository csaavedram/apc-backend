package com.apcemedicom.dtos;

public class CreatePaymentDto {
  public String token;
  public String issuer_id;
  public String payment_method_id;
  public Double transaction_amount;
  public Integer installments;
  public String description;
  public Payer payer;
  
  public String getToken() {
    return token;
}

public String getIssuerId() {
    return issuer_id;
}

public String getPaymentMethodId() {
    return payment_method_id;
}

public Double getTransactionAmount() {
    return transaction_amount;
}

public Integer getInstallments() {
    return installments;
}

public String getDescription() {
    return description;
}

public Payer getPayer() {
    return payer;
}

public static class Payer {
    public String email;
    public Identification identification;

    public String getEmail() {
        return email;
    }

    public Identification getIdentification() {
        return identification;
    }
}

public static class Identification {
    public String type;
    public String number;

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }
}
}
