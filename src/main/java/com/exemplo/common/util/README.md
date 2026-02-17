# Util (Utilitarios)

## O Que Colocar Aqui

Classes **utilitarias** com metodos estaticos ou helpers reutilizaveis.

## Tipos de Utilitarios

### 1. Formatadores
```java
DateUtils.formatarDataBr(LocalDate data)
StringUtils.removerAcentos(String texto)
NumberUtils.formatarMoeda(BigDecimal valor)
```

### 2. Validadores
```java
CpfUtils.isValido(String cpf)
EmailUtils.isValido(String email)
```

### 3. Conversores
```java
JsonUtils.toJson(Object obj)
Base64Utils.encode(String texto)
```

### 4. Geradores
```java
TokenUtils.gerarToken()
CodigoUtils.gerarCodigoAleatorio(int tamanho)
```

## Estrutura Recomendada

```java
public final class DateUtils {
    
    private DateUtils() {
    }
    
    public static String formatarDataBr(LocalDate data) {
        if (data == null) {
            return null;
        }
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
```

## Caracteristicas de Classes Utilitarias

1. **Classe final**: Nao deve ser extendida
2. **Construtor privado**: Nao deve ser instanciada
3. **Metodos estaticos**: Acessiveis sem instancia
4. **Sem estado**: Nao guardam dados
5. **Null-safe**: Tratam entradas nulas

## O Que NAO Colocar

- Logica de negocio (vai no Service)
- Acesso a banco (vai no Repository)
- Beans do Spring (vai no Config)
- Metodos que precisam de dependencias injetadas

## Boas Praticas

1. **Nomes claros**: XxxUtils, XxxHelper
2. **Uma responsabilidade**: DateUtils so para datas
3. **Documente**: JavaDoc nos metodos publicos
4. **Testes**: Utils devem ter alta cobertura
5. **Null-safety**: Sempre valide entradas

## Quando Criar um Utilitario

- Codigo repetido em varios lugares
- Operacao pura (sem efeitos colaterais)
- Funcao generica nao especifica de dominio
