package com.apcemedicom.configuraciones;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MercadoPago{
  
  @Value("${mercadopago.access-token}")
  private String accessToken;
  
  @PostConstruct
  public void init() {
    MercadoPagoConfig.setAccessToken(accessToken);
  }
}
