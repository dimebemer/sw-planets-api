package com.starwars.swplanetsapi.repository;

import com.starwars.swplanetsapi.model.document.Planeta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PlanetaRepository extends ReactiveCrudRepository<Planeta, String> {
    Flux<Planeta> findByNomeContainingIgnoreCase(String nome);
}
