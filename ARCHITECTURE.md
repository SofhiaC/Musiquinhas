# 📊 Arquitetura do Musiquinhas

## Diagrama de Fluxo (MVC)

```
┌─────────────────────────────────────────────────────────┐
│                    USUÁRIO (Interface)                  │
│              (Tela de Login - JavaFX)                   │
└────────────────────────┬────────────────────────────────┘
                         │
                    "sofhia@email.com"
                         │ 1234
                         ↓
    ┌────────────────────────────────────────┐
    │          VIEW (view.LoginView)         │
    │     - UI em JavaFX                     │
    │     - Botão "Entrar"                   │
    │     - Campo Email/Senha                │
    └────────────┬───────────────────────────┘
                 │
                 │ controller.autenticar()
                 ↓
    ┌────────────────────────────────────────┐
    │    CONTROLLER (UsuarioController)      │
    │     - Valida credenciais               │
    │     - Gerencia sessão (usuarioLogado)  │
    │     - Chama DAO para buscar usuário    │
    └────────────┬───────────────────────────┘
                 │
                 │ dao.buscarPorEmail()
                 ↓
    ┌────────────────────────────────────────┐
    │         DAO (UsuarioDAO)               │
    │     - Consulta banco de dados          │
    │     - Retorna objeto Usuario           │
    └────────────┬───────────────────────────┘
                 │
                 │ EntityManager.find()
                 ↓
    ┌────────────────────────────────────────┐
    │      DATABASE (H2 em memória)          │
    │     SELECT * FROM usuarios             │
    │     WHERE email = 'sofhia@...'         │
    └────────────────────────────────────────┘
```

---

## 📂 Estrutura de Pastas

```
Musiquinhas/
│
├── src/
│   ├── entities/                 # Modelos de dados (JPA)
│   │   ├── Usuario.java
│   │   ├── Musica.java
│   │   ├── Album.java
│   │   ├── Playlist.java
│   │   ├── Artista.java
│   │   ├── Assinatura.java
│   │   └── Avaliacao.java
│   │
│   ├── dao/                      # Acesso ao banco de dados
│   │   ├── UsuarioDAO.java
│   │   ├── MusicaDAO.java
│   │   ├── AlbumDAO.java
│   │   ├── PlaylistDAO.java
│   │   ├── ArtistaDAO.java
│   │   ├── AssinaturaDAO.java
│   │   └── AvaliacaoDAO.java
│   │
│   ├── controller/               # Lógica de negócio
│   │   ├── UsuarioController.java
│   │   └── (adicionar outros controllers)
│   │
│   ├── view/                     # Interface gráfica (JavaFX)
│   │   ├── Main.java             # Entrypoint
│   │   ├── LoginView.java        # Tela de login
│   │   ├── HomeView.java         # Tela principal
│   │   └── (outras views)
│   │
│   ├── resources/                # Imagens e assets
│   │   └── Musiquinhas.png
│   │
│   └── META-INF/                 # Configuração JPA
│       └── persistence.xml
│
├── pom.xml                       # Configuração Maven (dependências)
├── README.md                     # Documentação principal
├── SETUP.md                      # Guia de setup
├── CONTRIBUTING.md               # Guia de contribuição
├── ARCHITECTURE.md               # Este arquivo
├── .env.example                  # Variáveis de ambiente (opcional)
└── .gitignore                    # Arquivos ignorados pelo git
```

---

## 🔄 Fluxo de Dados

### Exemplo: Login do Usuário

1. **Usuário digita email e senha** na tela de login (JavaFX)
   ```
   Email: sofhia@email.com
   Senha: 1234
   ```

2. **Clica em "Entrar"** → Chama `LoginView.onLogin()`
   ```java
   Usuario user = controller.autenticar(email, senha);
   ```

3. **Controller valida** → Chama `UsuarioDAO.buscarPorEmail()`
   ```java
   Usuario u = dao.buscarPorEmail("sofhia@email.com");
   if (u != null && senha.equals(u.getSenha())) {
       usuarioLogado = u;
       return u;
   }
   ```

4. **DAO consulta banco** → Busca na tabela `usuarios`
   ```sql
   SELECT * FROM usuarios WHERE email = 'sofhia@email.com'
   ```

5. **H2 retorna resultado** → DAO converte para objeto Java
   ```java
   Usuario { id: 1, nome: "Sofhia", email: "sofhia@email.com", ... }
   ```

6. **Controller retorna Usuario** → View abre HomeView
   ```java
   // Login bem-sucedido!
   new HomeView("Sofhia").start(novoStage);
   ```

---

## 🗄️ Banco de Dados

### Tabela: usuarios

```sql
CREATE TABLE usuarios (
    ID BIGINT IDENTITY NOT NULL,
    EMAIL VARCHAR NOT NULL UNIQUE,
    NOME VARCHAR,
    SENHA VARCHAR,
    PRIMARY KEY (ID)
)
```

### Dados de Teste

| ID | EMAIL | NOME | SENHA |
|----|----|----|----|
| 1 | sofhia@email.com | Sofhia | 1234 |

> ℹ️ Inserido automaticamente na primeira execução!

---

## 🛠️ Stack Tecnológico

```
┌─────────────────────────────────────────┐
│        Camada de Apresentação           │
│           JavaFX 21.0.4                 │
│  (views: LoginView, HomeView, etc)      │
└────────────────┬────────────────────────┘
                 │
┌─────────────────────────────────────────┐
│        Camada de Lógica                 │
│       Controller (UsuarioController)    │
│       (autenticação, validações)        │
└────────────────┬────────────────────────┘
                 │
┌─────────────────────────────────────────┐
│      Camada de Persistência             │
│    DAO (UsuarioDAO, MusicaDAO, etc)     │
│       JPA/EclipseLink 4.0.8             │
└────────────────┬────────────────────────┘
                 │
┌─────────────────────────────────────────┐
│           Banco de Dados                │
│         H2 Database 2.4.240             │
│       (em memória ou arquivo)           │
└─────────────────────────────────────────┘
```

---

## 🚀 Como Adicionar Uma Nova Entidade

### Exemplo: Criar uma tabela "Gênero"

#### 1️⃣ Crie a Entidade (src/entities/Genero.java)
```java
@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    // Getters e Setters...
}
```

#### 2️⃣ Crie o DAO (src/dao/GeneroDAO.java)
```java
public class GeneroDAO {
    private EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("_musiquinhasPU");
    
    public void criar(Genero genero) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(genero);
        em.getTransaction().commit();
        em.close();
    }
    
    // Outros métodos (buscar, atualizar, deletar, listar)...
}
```

#### 3️⃣ Crie o Controller (src/controller/GeneroController.java)
```java
public class GeneroController {
    private GeneroDAO dao = new GeneroDAO();
    
    public boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }
    
    public void salvarGenero(String nome) {
        if (!validarNome(nome)) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        Genero genero = new Genero();
        genero.setNome(nome);
        dao.criar(genero);
    }
}
```

#### 4️⃣ Crie a View (src/view/GeneroView.java)
```java
public class GeneroView extends Application {
    private GeneroController controller = new GeneroController();
    
    @Override
    public void start(Stage stage) {
        // Seu UI aqui...
    }
}
```

---

## 📝 Padrões de Código

### ✅ Bom
```java
// Entidade com anotações JPA
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;
}

// DAO com métodos claros
public class UsuarioDAO {
    public void criar(Usuario u) { ... }
    public Usuario buscarPorId(Long id) { ... }
    public Usuario buscarPorEmail(String email) { ... }
    public List<Usuario> listarTodos() { ... }
}

// Controller com validações
public class UsuarioController {
    public Usuario autenticar(String email, String senha) {
        if (email == null || senha == null) return null;
        Usuario u = dao.buscarPorEmail(email);
        return (u != null && senha.equals(u.getSenha())) ? u : null;
    }
}

// View em JavaFX
public class LoginView extends Application {
    @Override
    public void start(Stage stage) {
        // UI com JavaFX
    }
}
```

---

## 🔗 Referências

- [Documentação Maven](https://maven.apache.org/)
- [Documentação JavaFX](https://openjfx.io/)
- [Jakarta Persistence](https://jakarta.ee/specifications/persistence/3.2/)
- [EclipseLink](https://www.eclipse.org/eclipselink/)
- [H2 Database](https://h2database.com/)

---
