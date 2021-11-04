package com.codingsaint.mediadeck.config;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "mediadeck.firebase.credendials")

@JsonSerialize(as = FirebaseCredentials.class)
public class FirebaseCredentials {
    private String type;
    private String project_id;
    private String private_key_id;
    private String private_key;
    private String client_email;
    private String client_id;
    private String auth_uri;
    private String token_uri;
    private String auth_provider_x509_cert_url;
    private String client_x509_cert_url;
}
