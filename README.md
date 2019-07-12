# SW Planets API
API de planetas do universo Star Wars. Feita com Spring Webflux.

## Requisitos
- Java 8
- Maven

## Execução
mvn clean install spring-boot:run

A API sobe com uma base MongoDB embutida que é zerada a cada novo boot da aplicação.

## Endpoints

### GET /planetas
Busca todos os planetas cadastrados.

Parâmetros: 
- nome -> Filtra a busca por nome do planeta (aceita trechos do nome)

```json
[
    {
        "id": "5d28e992e8bb9a1f18318ab3",
        "nome": "Bespin",
        "clima": "frio",
        "terreno": "esburacado",
        "qtdAparicoesFilmes": 1
    }
]
``` 

### GET /planetas/{id}
Retorna um planeta pelo seu ID.

Ex: GET /planetas/5d28e992e8bb9a1f18318ab3
```json
{
    "id": "5d28e992e8bb9a1f18318ab3",
    "nome": "Bespin",
    "clima": "frio",
    "terreno": "esburacado",
    "qtdAparicoesFilmes": 1
}
```

### POST /planetas
Cria um novo planeta. O nome escolhido precisa constar na base oficial do https://swapi.co/

Payload:
```json
{
    "nome": "Bespin",
    "clima": "frio",
    "terreno": "esburacado"
}
```

Retorno:
```json
{
    "id": "5d28e992e8bb9a1f18318ab3",
    "nome": "Bespin",
    "clima": "frio",
    "terreno": "esburacado",
    "qtdAparicoesFilmes": 1
}
```

### DELETE /planetas/{id}
Exclui um planeta cadastrado pelo seu ID.

Ex: DELETE /planetas/5d28e992e8bb9a1f18318ab3

### GET /dados-oficiais/planetas
Retorna todos os planetas da base oficial do https://swapi.co/

Obs: A implementação atual retorna apenas a primeira página da API.

```json
{
    "count": 61,
    "next": "https://swapi.co/api/planets/?page=2",
    "previous": null,
    "results": [
        {
            "name": "Alderaan",
            "rotation_period": "24",
            "orbital_period": "364",
            "diameter": "12500",
            "climate": "temperate",
            "gravity": "1 standard",
            "terrain": "grasslands, mountains",
            "surface_water": "40",
            "population": "2000000000",
            "residents": [
                "https://swapi.co/api/people/5/",
                "https://swapi.co/api/people/68/",
                "https://swapi.co/api/people/81/"
            ],
            "films": [
                "https://swapi.co/api/films/6/",
                "https://swapi.co/api/films/1/"
            ],
            "created": "2014-12-10T11:35:48.479",
            "edited": "2014-12-20T20:58:18.42",
            "url": "https://swapi.co/api/planets/2/"
        },
        {
            "name": "Yavin IV",
            "rotation_period": "24",
            "orbital_period": "4818",
            "diameter": "10200",
            "climate": "temperate, tropical",
            "gravity": "1 standard",
            "terrain": "jungle, rainforests",
            "surface_water": "8",
            "population": "1000",
            "residents": [],
            "films": [
                "https://swapi.co/api/films/1/"
            ],
            "created": "2014-12-10T11:37:19.144",
            "edited": "2014-12-20T20:58:18.421",
            "url": "https://swapi.co/api/planets/3/"
        }
    ]
}
```
