package com.starwars.swplanetsapi.factory;

import com.starwars.swplanetsapi.service.PlanetaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.server.ServerRequest;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class PlanetaBuscaFactoryTest {

    private static final String MOCK_NOME = "Bespin";

    @InjectMocks
    private PlanetaBuscaFactory factory;

    @Mock
    private PlanetaService service;

    private ServerRequest request;

    @Before
    public void setUp() {
        factory = new PlanetaBuscaFactory(service);
    }

    @Test
    public void deveRealizarBuscaGeral() {
        dadoUmaChamadaSemParametroNome();
        aoBuscar();
        deveListarTodosPlanetas();
    }

    @Test
    public void deveRealizarBuscaPorNome() {
        dadoUmaChamadaComParametroNome();
        aoBuscar();
        deveBuscarPorNome();
    }

    private void dadoUmaChamadaSemParametroNome() {
        request = MockServerRequest.builder().build();
    }

    private void dadoUmaChamadaComParametroNome() {
        request = MockServerRequest.builder()
                .queryParam("nome", MOCK_NOME)
                .build();
    }

    private void aoBuscar() {
        factory.buscar(request);
    }

    private void deveBuscarPorNome() {
        verify(service).buscarPorNome(MOCK_NOME);
    }

    private void deveListarTodosPlanetas() {
        verify(service).listar();
    }

}