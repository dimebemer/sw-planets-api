package com.starwars.swplanetsapi.service;

import com.starwars.swplanetsapi.client.OfficialPlanetClient;
import com.starwars.swplanetsapi.model.document.Planeta;
import com.starwars.swplanetsapi.model.dto.HateoasWrapperDTO;
import com.starwars.swplanetsapi.model.dto.OfficialPlanetDTO;
import com.starwars.swplanetsapi.model.exception.NotFoundException;
import com.starwars.swplanetsapi.repository.PlanetaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PlanetaServiceTest {

    private static final String MOCK_NOME = "Bespin";

    @InjectMocks
    private PlanetaService service;

    @Mock
    private PlanetaRepository repository;

    @Mock
    private OfficialPlanetClient client;

    private HateoasWrapperDTO<OfficialPlanetDTO> planetaOficial;
    private Planeta planeta;
    private StepVerifier.FirstStep<Planeta> execucao;

    @Before
    public void setUp() throws Exception {
        service = new PlanetaService(repository, client);
    }

    @Test
    public void deveCriarQuandoExistirUmPlanetaComONomeEspecificado() {
        dadoUmNovoPlanetaASerCriado();
        dadoUmPlanetaOficial();
        comMesmoNomeDoNovoPlaneta();
        aoCriar();
        deveCriarNovoPlaneta();
        deveConterQuantidadeDeAparicoesEmFilmesDoPlanetaOficial();
    }

    @Test
    public void naoDeveCriarQuandoNaoExistirUmPlanetaComONomeEspecificado() {
        dadoUmNovoPlanetaASerCriado();
        dadoUmPlanetaOficial();
        comNomeDiferenteDoNovoPlaneta();
        aoCriar();
        naoDeveCriarNovoPlaneta();
        deveLancarErroNaoEncontrado();
    }

    private void deveLancarErroNaoEncontrado() {
        execucao.expectError(NotFoundException.class);
    }

    private void naoDeveCriarNovoPlaneta() {
        execucao.then(() -> verify(repository, never()).save(planeta));
    }

    private void comNomeDiferenteDoNovoPlaneta() {
        when(client.findByName(MOCK_NOME)).thenReturn(Mono.empty());
    }

    private void deveConterQuantidadeDeAparicoesEmFilmesDoPlanetaOficial() {
        execucao.assertNext(planeta -> assertThat(planeta.getQtdAparicoesFilmes(), is(2L)));
    }

    private void deveCriarNovoPlaneta() {
        execucao.then(() -> verify(repository).save(planeta));
    }

    private void aoCriar() {
        execucao = StepVerifier.create(service.criar(planeta));
    }

    private void dadoUmNovoPlanetaASerCriado() {
        planeta = new Planeta();
        planeta.setNome(MOCK_NOME);
    }

    private void dadoUmPlanetaOficial() {
        planetaOficial = new HateoasWrapperDTO<>();

        OfficialPlanetDTO planeta = new OfficialPlanetDTO();
        planeta.setName(MOCK_NOME);
        planeta.setFilms(asList("Star Wars IV", "Star Wars V"));

        planetaOficial.setResults(singletonList(planeta));
    }

    private void comMesmoNomeDoNovoPlaneta() {
        when(client.findByName(MOCK_NOME)).thenReturn(Mono.just(planetaOficial));
    }

}