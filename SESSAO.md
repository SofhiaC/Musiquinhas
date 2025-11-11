# 🔐 Mantendo Sessão do Usuário em Múltiplas Telas

## ✅ Solução: SessionManager (Singleton)

Agora você tem um **SessionManager** que mantém a sessão do usuário em toda a aplicação!

---

## 🎯 Como Funciona

```java
// SessionManager guarda TUDO
SessionManager.getInstance().setUsuarioLogado(usuario);

// Qualquer tela acessa os dados completos
public class MinhaTelaView extends Application {
    private final SessionManager session = SessionManager.getInstance();
    
    Usuario user = session.getUsuarioLogado();  // Acesso completo!
}
```

---

## 🚀 Como Usar em Suas Telas

### **Passo 1: Importar SessionManager**

```java
import controller.SessionManager;
```

### **Passo 2: Obter a Instância (Singleton)**

```java
public class MinhaView extends Application {
    private final SessionManager session = SessionManager.getInstance();
    
    @Override
    public void start(Stage stage) {
        // Pronto para usar!
    }
}
```

### **Passo 3: Acessar Dados do Usuário**

```java
// Opção A: Objeto completo
Usuario usuario = session.getUsuarioLogado();
String nome = usuario.getNome();
String email = usuario.getEmail();
Long id = usuario.getId();

// Opção B: Métodos diretos
String nome = session.getNomeUsuario();
String email = session.getEmailUsuario();
boolean estaLogado = session.isLogado();
```

---

## 📚 API do SessionManager

### **Métodos Disponíveis**

```java
// Salvar usuário na sessão
SessionManager.getInstance().setUsuarioLogado(usuario);

// Obter usuário completo
Usuario user = SessionManager.getInstance().getUsuarioLogado();

// Verificar se está logado
boolean logado = SessionManager.getInstance().isLogado();

// Fazer logout
SessionManager.getInstance().logout();

// Obter dados específicos
String nome = SessionManager.getInstance().getNomeUsuario();
String email = SessionManager.getInstance().getEmailUsuario();
```

---

## Então da pra Fazer Assim

```java
// 1. Sempre verificar se está logado
if (session.isLogado()) {
    Usuario user = session.getUsuarioLogado();
    // usar dados...
}

// 2. Tratar null
Usuario user = session.getUsuarioLogado();
if (user != null) {
    // usar dados...
}

// 3. Fazer logout ao sair
btnLogout.setOnAction(e -> {
    session.logout();
    new LoginView().start(new Stage());
    stage.close();
});
```
---

## 🔄 Fluxo Completo

```
1. LoginView
   ↓
   usuario clica "Entrar"
   ↓
   UsuarioController.autenticar()
   ↓
   SessionManager.setUsuarioLogado(usuario)  ← SALVA NA SESSÃO
   ↓
   HomeView abre
   ↓
2. HomeView (ou qualquer outra tela)
   ↓
   Usuario user = SessionManager.getInstance().getUsuarioLogado()  ← ACESSA
   ↓
   Pode acessar: id, nome, email, senha (cuidado!)
   ↓
3. Usuário clica "Sair" (logout)
   ↓
   SessionManager.logout()  ← LIMPA A SESSÃO
   ↓
   Volta para LoginView
```
