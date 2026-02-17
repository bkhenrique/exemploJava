# Config (Configuracoes)

## O Que Colocar Aqui

Classes de **configuracao do Spring** e beans customizados.

## Tipos de Configuracao

### 1. Beans de Terceiros
```java
@Bean
public ObjectMapper objectMapper() { ... }

@Bean
public RestTemplate restTemplate() { ... }
```

### 2. Configuracao de Bibliotecas
```java
@Configuration
public class SwaggerConfig { ... }

@Configuration
public class CorsConfig { ... }
```

### 3. Properties Tipadas
```java
@ConfigurationProperties(prefix = "app")
public class AppProperties { ... }
```

## Anotacoes Comuns

```java
@Configuration        // Marca classe de configuracao
@Bean                 // Define um bean gerenciado pelo Spring
@Value                // Injeta valor de propriedade
@ConfigurationProperties  // Mapeia grupo de propriedades
@EnableCaching        // Habilita cache
@EnableScheduling     // Habilita agendamento
@EnableAsync          // Habilita execucao assincrona
@Profile              // Ativa apenas em perfil especifico
```

## Organizacao de Arquivos

```
config/
├── AppConfig.java           # Configuracoes gerais da app
├── CorsConfig.java          # CORS
├── SwaggerConfig.java       # OpenAPI/Swagger
├── CacheConfig.java         # Configuracao de cache
├── AsyncConfig.java         # Configuracao de async
├── properties/
│   └── AppProperties.java   # Properties tipadas
```

## Boas Praticas

1. **Uma responsabilidade**: Cada @Configuration para um proposito
2. **Nomes claros**: XxxConfig para classes de configuracao
3. **Profiles**: Use @Profile para configs ambiente-especificas
4. **Properties tipadas**: Prefira @ConfigurationProperties a @Value
5. **Documente**: Explique o proposito de cada bean

## Quando Criar Configuracao

- Precisa customizar biblioteca de terceiros
- Quer criar beans reutilizaveis
- Configuracoes que variam por ambiente
- Habilitar features do Spring
