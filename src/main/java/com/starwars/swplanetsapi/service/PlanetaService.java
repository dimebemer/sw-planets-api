package com.starwars.swplanetsapi.service;

import com.starwars.swplanetsapi.client.OfficialPlanetClient;
import com.starwars.swplanetsapi.model.document.Planeta;
import com.starwars.swplanetsapi.model.exception.NotFoundException;
import com.starwars.swplanetsapi.repository.PlanetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlanetaService {

    private final PlanetaRepository repository;
    private final OfficialPlanetClient client;

    public Mono<Planeta> criar(Planeta planeta) {
        return client.findByName(planeta.getNome())
                .flatMapMany(wrapper -> Flux.fromIterable(wrapper.getResults()))
                .switchIfEmpty(Mono.error(new NotFoundException("Não foi encontrado nenhum planeta oficial com este nome")))
                .flatMap(result -> Flux.fromIterable(result.getFilms()))
                .count()
                .doOnNext(planeta::setQtdAparicoesFilmes)
                .flatMap(ignore -> repository.save(planeta));
    }

    public Flux<Planeta> listar() {
        return repository.findAll();
    }

    public Mono<Planeta> buscar(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Não foi encontrado nenhum planeta com este ID")));
    }

    public Flux<Planeta> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Mono<Void> excluir(String id) {
        return buscar(id)
                .flatMap(planeta -> repository.deleteById(planeta.getId()));
    }

}
