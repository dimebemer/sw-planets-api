package com.starwars.swplanetsapi.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Planeta {
    @Id
    private String id;
    private String nome;
    private String clima;
    private String terreno;
    private Long qtdAparicoesFilmes;
}
