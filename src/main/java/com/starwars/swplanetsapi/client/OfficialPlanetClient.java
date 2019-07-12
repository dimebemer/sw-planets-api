package com.starwars.swplanetsapi.client;

import com.starwars.swplanetsapi.model.dto.HateoasWrapperDTO;
import com.starwars.swplanetsapi.model.dto.OfficialPlanetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class OfficialPlanetClient {

    private final WebClient swapiClient;

    public Mono<HateoasWrapperDTO<OfficialPlanetDTO>> findAll() {
        return swapiClient
                .get()
                .uri("/planets/")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<HateoasWrapperDTO<OfficialPlanetDTO>>() {});
    }

    public Mono<HateoasWrapperDTO<OfficialPlanetDTO>> findByName(String name) {
        return swapiClient
                .get()
                .uri("/planets/?search=" + name)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<HateoasWrapperDTO<OfficialPlanetDTO>>() {});
    }

}
