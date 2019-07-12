package com.starwars.swplanetsapi.rest.controller;

import com.starwars.swplanetsapi.client.OfficialPlanetClient;
import com.starwars.swplanetsapi.model.dto.HateoasWrapperDTO;
import com.starwars.swplanetsapi.model.dto.OfficialPlanetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Criado em forma de Controller tradicional do Spring apenas para que a aplicação faça
 * uso das duas formas de implementação vigentes para o teste de recrutamento.
 */
@RequestMapping("/dados-oficiais/planetas")
@RestController
@RequiredArgsConstructor
public class OfficialPlanetController {

    private final OfficialPlanetClient client;

    @GetMapping
    public Mono<HateoasWrapperDTO<OfficialPlanetDTO>> listar() {
        return client.findAll();
    }

}
