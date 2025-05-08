package com.apcemedicom.dtos;

public class CreatePaymentDto {
  public String token;
  public String issuer_id;
  public String payment_method_id;
  public Double transaction_amount;
  public Integer installments;
  public String description;
  public Payer payer;
  
  public static class Payer {
    public String email;
    public Identification identification;
  }
  
  public static class Identification {
    public String type;
    public String number;
  }
}
