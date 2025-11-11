# 🤝 Guia de Contribuição - Musiquinhas

Bem-vindo! Este arquivo explica como você pode contribuir para o projeto Musiquinhas.

---

## 📋 Antes de Começar

1. Verifique se você seguiu [SETUP.md](SETUP.md) para configurar seu ambiente
2. Certifique-se de que consegue executar: `mvn clean compile exec:java`
3. Faça um branch para sua feature ou nome: `git checkout -b feature/sua-funcionalidade`

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão **MVC em 3 camadas**:

```
view/           → Interface gráfica (JavaFX)
     ↓
controller/     → Lógica de negócio (autenticação, validações)
     ↓
dao/            → Acesso ao banco de dados
     ↓
entities/       → Classes JPA (modelos de dados)
```

### 📂 Adicionar Uma Nova Funcionalidade

1. **Crie a entidade JPA** em `src/entities/`
   ```java
   @Entity
   @Table(name = "sua_tabela")
   public class SuaEntidade {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       // ... seus campos
   }
   ```

2. **Crie o DAO** em `src/dao/`
   ```java
   public class SuaEntidadeDAO {
       public void criar(SuaEntidade obj) { ... }
       public SuaEntidade buscarPorId(Long id) { ... }
       // ... outros métodos
   }
   ```

3. **Crie o Controller** em `src/controller/`
   ```java
   public class SuaEntidadeController {
       private SuaEntidadeDAO dao = new SuaEntidadeDAO();
       
       public boolean validarDados(SuaEntidade obj) { ... }
       public void salvar(SuaEntidade obj) { ... }
   }
   ```

4. **Crie a View** em `src/view/`
   ```java
   public class SuaView extends Application {
       @Override
       public void start(Stage stage) {
           // Seu UI com JavaFX
       }
   }
   ```

---

## 🧪 Testando Suas Mudanças

Sempre compile e teste antes de fazer commit:

```bash
mvn clean compile exec:java
```

---

## 📝 Padrões de Código

### 1. **Nomes de Classes**
- Entidades: `Usuario`, `Musica`, `Playlist` (PascalCase)
- Controllers: `UsuarioController`, `MusicaController` (PascalCase + Controller)
- DAOs: `UsuarioDAO`, `MusicaDAO` (PascalCase + DAO)
- Views: `LoginView`, `HomeView`, `UsuarioView` (PascalCase + View)

### 2. **Nomes de Variáveis**
```java
// ✅ BOM
private String nomeUsuario;
private List<Musica> minhasMusicas;

// ❌ RUIM
private String nusr;
private List<Musica> ml;
```

### 3. **Comentários**
```java
// ✅ BOM - Explica o POR QUE
// Verifica se usuário está logado antes de liberar acesso
if (usuarioLogado == null) {
    mostrarAlerta("Faça login primeiro!");
    return;
}

// ❌ RUIM - Óbvio
// Verifica se usuário é nulo
if (usuarioLogado == null) { ... }
```

### 4. **Tratamento de Exceções**
```java
// ✅ BOM
try {
    usuario = dao.criar(usuario);
} catch (PersistenceException e) {
    logger.error("Erro ao salvar usuário", e);
    mostrarAlerta("Erro ao salvar: " + e.getMessage());
}

// ❌ RUIM
try {
    usuario = dao.criar(usuario);
} catch (Exception e) {
    e.printStackTrace();
}
```

---

## 🔄 Fluxo de Contribuição

1. **Faça um branch:**
   ```bash
   git checkout -b feature/descricao-breve
   ```

2. **Faça suas mudanças** e teste localmente:
   ```bash
   mvn clean compile exec:java
   ```

3. **Commit com mensagem clara:**
   ```bash
   git commit -m "feat: adiciona login com email"
   ```

4. **Push para o repositório**

## 📚 Recursos

- [Java 21 Docs](https://docs.oracle.com/en/java/javase/21/)
- [JavaFX 21 Docs](https://openjfx.io/)
- [JPA/EclipseLink Docs](https://www.eclipse.org/eclipselink/)
- [H2 Database Docs](https://h2database.com/)
- [Maven Docs](https://maven.apache.org/guides/)

---

## ❓ Dúvidas?

- Verifique o [README.md](README.md)
- Leia o [SETUP.md](SETUP.md)

---


