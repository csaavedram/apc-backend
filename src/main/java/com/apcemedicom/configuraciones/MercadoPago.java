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
    System.out.println("🔧 === CONFIGURANDO MERCADOPAGO ===");
    System.out.println("🔑 Access Token: " + accessToken);
    
    if (accessToken == null || accessToken.trim().isEmpty()) {
      System.err.println("❌ ERROR: Access token está vacío o null!");
      throw new RuntimeException("MercadoPago access token no configurado");
    }
    
    if (!accessToken.startsWith("TEST-") && !accessToken.startsWith("APP_USR-")) {
      System.err.println("❌ WARNING: Access token no parece válido (no empieza con TEST- o APP_USR-)");
    }
    
    MercadoPagoConfig.setAccessToken(accessToken);
    
    System.out.println("✅ MercadoPago configurado correctamente");
    System.out.println("🌍 Environment: " + (accessToken.startsWith("TEST-") ? "SANDBOX" : "PRODUCTION"));
  }
}
