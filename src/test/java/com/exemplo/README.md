# Testes

## Estrutura de Testes

```
src/test/java/com/exemplo/
├── feature/
│   └── usuario/
│       ├── controller/
│       │   └── UsuarioControllerTest.java    # Testes de integracao
│       ├── service/
│       │   └── UsuarioServiceTest.java       # Testes unitarios
│       └── repository/
│           └── UsuarioRepositoryTest.java    # Testes de repositorio
│
└── common/
    └── util/
        └── DateUtilsTest.java                # Testes de utilitarios
```

## Tipos de Teste

### 1. Testes Unitarios
Testam uma classe isoladamente, mockando dependencias.

```java
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository repository;
    
    @InjectMocks
    private UsuarioService service;
    
    @Test
    void deveCriarUsuario() { ... }
}
```

### 2. Testes de Integracao
Testam multiplas camadas juntas.

```java
@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void deveListarUsuarios() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios"))
               .andExpect(status().isOk());
    }
}
```

### 3. Testes de Repositorio
Testam queries customizadas.

```java
@DataJpaTest
class UsuarioRepositoryTest {
    
    @Autowired
    private UsuarioRepository repository;
    
    @Test
    void deveBuscarPorEmail() { ... }
}
```

## Anotacoes Comuns

```java
@Test                    // Marca metodo como teste
@DisplayName            // Nome legivel do teste
@BeforeEach             // Executa antes de cada teste
@AfterEach              // Executa apos cada teste
@BeforeAll              // Executa uma vez antes de todos
@Disabled               // Desabilita teste temporariamente
@ParameterizedTest      // Teste com multiplos parametros
@Mock                   // Cria mock do Mockito
@InjectMocks            // Injeta mocks no objeto testado
@Spy                    // Mock parcial
@Captor                 // Captura argumentos
```

## Padroes de Nomenclatura

```java
// Padrao: deve[Comportamento]_quando[Condicao]
void deveCriarUsuario_quandoDadosValidos()
void deveLancarExcecao_quandoEmailDuplicado()
void deveRetornarVazio_quandoNaoEncontrar()
```

## Estrutura AAA (Arrange-Act-Assert)

```java
@Test
void deveCriarUsuario() {
    // Arrange - preparacao
    CriarUsuarioRequest request = new CriarUsuarioRequest("Nome", "email@teste.com", "senha");
    when(repository.save(any())).thenReturn(new Usuario(...));
    
    // Act - execucao
    UsuarioResponse response = service.criar(request);
    
    // Assert - verificacao
    assertNotNull(response);
    assertEquals("Nome", response.getNome());
    verify(repository, times(1)).save(any());
}
```

## Boas Praticas

1. **Um assert por teste**: Facilita identificar falhas
2. **Nomes descritivos**: Teste conta o que faz
3. **Independencia**: Testes nao dependem de ordem
4. **Dados isolados**: Cada teste cria seus dados
5. **Cobertura**: Minimo 80% em services
