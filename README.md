# 🎵 Musiquinhas

Sistema de Música com **JavaFX**, **JPA/EclipseLink** e **H2 Database**.

---

## ⚡ Quick Start

### Pré-requisitos
- **Java 21+** instalado ([Download](https://www.oracle.com/java/technologies/downloads/#java21))
- **Maven 3.8.9+** instalado ([Download](https://maven.apache.org/download.cgi))

### Instruções de Setup

1. **Clone o repositório:**
   ```bash
   git clone <seu-repositorio>
   cd Musiquinhas
   ```

2. **Compile o projeto:**
   ```bash
   mvn clean compile
   ```
   Maven baixará automaticamente todas as dependências:
   - ✅ JavaFX 21.0.4
   - ✅ EclipseLink 4.0.8
   - ✅ H2 Database 2.4.240
   - ✅ Jakarta Persistence API

3. **Execute a aplicação:**
   ```bash
   mvn exec:java
   ```

4. **Login com usuário de teste:**
- Email: sofhia@email.com   
Senha: 1234

- Email: joao@email.com  
Senha: senha123

- Email: maria@email.com  
Senha: maria456

---

## 📁 Estrutura do Projeto

```
src/
├── controller/          # Lógica de negócio (autenticação, validações)
├── dao/                 # Data Access Objects (acesso ao banco de dados)
├── entities/            # Classes JPA que representam tabelas
├── view/                # Interface gráfica JavaFX
├── resources/           # Imagens e assets
└── META-INF/            # Configuração JPA (persistence.xml)
```

---

## 🛠️ Tecnologias

| Componente | Versão |
|---|---|
| **Java** | 21 |
| **JavaFX** | 21.0.4 |
| **JPA/EclipseLink** | 4.0.8 |
| **Jakarta Persistence** | 3.2.0 |
| **H2 Database** | 2.4.240 |
| **Build Tool** | Maven 3.8.9+ |

---

## 📚 Recursos Úteis

- **Diagrama de Classes:** [Google Drive](https://drive.google.com/file/d/1RB2pSDZ7NxxXuDmupfmntSblDXcKu5tq/view?usp=sharing)
- **Design (Figma):** [Protótipo](https://www.figma.com/design/ZbL1iXkfnI5Q0tlUnxnjfz/Untitled?node-id=0-1&t=6itlZXmDYFiaCuYs-1)

---

## 💡 Comandos Úteis

| Comando | Descrição |
|---|---|
| `mvn clean compile` | Limpa e compila o projeto |
| `mvn exec:java` | Executa a aplicação |
| `mvn clean compile exec:java` | Limpa, compila e executa em um comando |

---

## 🔧 Troubleshooting

### "Maven command not found"
- Instale Maven: [maven.apache.org](https://maven.apache.org)
- Adicione ao PATH: `C:\apache-maven-3.x.x\bin` (Windows) ou `/usr/local/bin` (Mac/Linux)

### "Java version mismatch"
- Instale Java 21: [oracle.com/java](https://www.oracle.com/java/technologies/downloads/#java21)
- Verifique: `java -version`

### "JavaFX not found"
- Maven vai baixar automaticamente - não precisa fazer nada!
- Se der erro, tente: `mvn clean compile -X` (modo debug)

---

## 📝 Nota

O banco de dados é **em memória (H2)**, então:
- ✅ Dados persistem **durante a sessão**
- ❌ Dados são **perdidos** quando a aplicação fecha

## 🗺️ Outros Manuais


1. **README.md** ← Esse arquivo
   - Visão geral do projeto
   - Quick start

2. **SETUP.md** ← Guia detalhado
   - Instruções para Windows/Mac/Linux
   - Troubleshooting
   - Como adicionar ao PATH

3. **ARCHITECTURE.md** ← Entenda a estrutura
   - Diagrama MVC
   - Como adicionar funcionalidades
   - Padrões de código

4. **CONTRIBUTING.md** ← Aprenda a contribuir
   - Padrões de código
   - Como fazer um PR
   - Estrutura do projeto

5. **SESSAO.md** ← Entenda como implementar a sessão
   - Padrão Singleton
   - Como acessar dados do usuário em qualquer tela
   - Exemplos de código

---
