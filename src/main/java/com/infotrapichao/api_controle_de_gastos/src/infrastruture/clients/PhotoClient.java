package com.infotrapichao.api_controle_de_gastos.src.infrastruture.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.integration.UploadPhotoRequest;

@Service
public class PhotoClient {

    private final RestClient restClient;

    public PhotoClient(
            @Value("${photo-service.url}") String url) {

        this.restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public void upload(
            String photoName,
            String imagemBase64,
            String authorization) {

        UploadPhotoRequest request = new UploadPhotoRequest(
                photoName,
                imagemBase64);

        restClient.post()
                .uri("/photos")
                .header("Authorization", authorization)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}
