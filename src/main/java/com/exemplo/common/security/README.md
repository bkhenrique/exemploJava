# Security (Seguranca e Autenticacao)

## O Que Colocar Aqui

Classes relacionadas a **autenticacao**, **autorizacao** e **seguranca** da aplicacao.

## Componentes Comuns

```
security/
├── SecurityConfig.java         # Configuracao do Spring Security
├── JwtAuthenticationFilter.java # Filtro de autenticacao JWT
├── JwtTokenProvider.java       # Geracao e validacao de tokens
├── UserDetailsServiceImpl.java # Carrega usuario para autenticacao
├── CustomAuthenticationEntryPoint.java # Handler de 401
└── CustomAccessDeniedHandler.java # Handler de 403
```

## SecurityConfig Basico

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

## Anotacoes de Autorizacao

```java
@PreAuthorize("hasRole('ADMIN')")           // Requer role ADMIN
@PreAuthorize("hasAuthority('user:read')")  // Requer authority especifica
@PreAuthorize("#id == authentication.principal.id") // Verifica ownership
@Secured("ROLE_USER")                       // Alternativa mais simples
```

## Fluxo de Autenticacao JWT

```
1. POST /api/auth/login (email, senha)
        │
        ▼
2. Valida credenciais
        │
        ▼
3. Gera JWT token
        │
        ▼
4. Retorna token ao cliente
        │
        ▼
5. Cliente envia token no header:
   Authorization: Bearer <token>
        │
        ▼
6. JwtAuthenticationFilter valida token
        │
        ▼
7. Request autorizada ou rejeitada
```

## Boas Praticas

1. **Nunca armazene senhas em texto**: Use BCryptPasswordEncoder
2. **Tokens curtos**: JWT com expiracao curta + refresh token
3. **HTTPS obrigatorio**: Em producao, sempre
4. **Rate limiting**: Proteja endpoints de login
5. **Logs de seguranca**: Registre tentativas de login
6. **Secrets seguros**: Use variaveis de ambiente para chaves

## O Que NAO Colocar

- Logica de negocio (vai no Service)
- Validacao de dados (vai no DTO)
- Regras especificas de dominio
