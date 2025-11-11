```
╔═══════════════════════════════════════════════════════════════════╗
║            "Como Clonar e Rodar o Projeto Musiquinhas"            ║
╚═══════════════════════════════════════════════════════════════════╝
```

# Resumo

```bash
# 1. Clonar
git clone <seu-repositorio>
cd Musiquinhas

# 2. Compilar (Maven baixa TUDO automaticamente)
mvn clean compile

# 3. Executar
mvn exec:java

# 4. Fazer Login (verificar LoginView caso queira inserir mais)

Email: sofhia@email.com
Senha: 1234

Email: joao@email.com
Senha: senha123

Email: maria@email.com
Senha: maria456
``` 

---

# 🎯 Passo a Passo Detalhado

## ✅ PASSO 1: Verificar Pré-requisitos

Abra o terminal/PowerShell e execute:

```bash
java -version
```

**Resultado esperado:**
```
java version "21.0.x" ... 
```

Se não tiver Java 21:
- [Baixe aqui](https://www.oracle.com/java/technologies/downloads/#java21)
- Instale
- Reinicie o terminal

---

## ✅ PASSO 2: Verificar Maven

```bash
mvn -version
```

**Resultado esperado:**
```
Apache Maven 3.8.9 (ou maior)
```

Se não tiver Maven:
- [Baixe aqui](https://maven.apache.org/download.cgi)
- Descompacte em `C:\apache-maven` ou `~/apache-maven`
- Adicione ao PATH (veja instruções no SETUP.md)
- Reinicie o terminal

---

## ✅ PASSO 3: Clonar o Repositório

```bash
git clone <seu-repositorio>
cd Musiquinhas
```

---

## ✅ PASSO 4: Compilar o Projeto

```bash
mvn clean compile
```

**O que Maven fará:**
- ✓ Baixar JavaFX 21.0.4
- ✓ Baixar EclipseLink 4.0.8
- ✓ Baixar H2 Database
- ✓ Compilar todo o código
- ✓ Preparar a pasta `/target`

---

## ✅ PASSO 5: Executar

```bash
mvn exec:java
```

**Resultado:**
- Uma janela JavaFX abre
- Você vê a tela de login
- Banco de dados é criado automaticamente
- Usuário de teste é inserido automaticamente

---

## ✅ PASSO 6: Fazer Login

Use estas credenciais:

| Campo | Valor |
|-------|-------|
| Email | sofhia@email.com |
| Senha | 1234 |

Clique em "Entrar" → Você entra na aplicação! 🎉

---

# 💡 Comandos Úteis

| Comando | O que faz |
|---------|-----------|
| `mvn clean compile` | Limpa e compila |
| `mvn exec:java` | Executa a aplicação |
| `mvn clean compile exec:java` | Tudo em um comando |
| `mvn -DskipTests compile` | Compila rápido (sem testes) |

---

# 🔴 Problemas Comuns

### "java -version" não funciona
→ Java não instalado. [Baixe Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

### "mvn -version" não funciona
→ Maven não instalado. [Baixe Maven](https://maven.apache.org/download.cgi) e siga SETUP.md

### "Unsupported JavaFX configuration"
→ É apenas um aviso, ignore! A aplicação vai funcionar.

### Login não funciona
→ Tente: `mvn clean compile exec:java` (às vezes a primeira execução demora msm)

### Erro "classnotfoundexception"
→ Rode: `mvn clean compile` novamente

---

# 📚 Documentação

Existem 4 documentos úteis:

1. **README.md** ← Comece aqui!
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

# 🤔 Dúvidas?

1. Leia SETUP.md (seção Troubleshooting)
2. Verifique se Java 21 está instalado
3. Verifique se Maven está no PATH
4. Tente: `mvn clean compile -X` (modo debug)

---

# ✨ Pronto!

Se conseguiu executar `mvn exec:java` e a janela JavaFX abriu, **YAYYYYYYYYYYYYYYYYY!** 🎉

Agora você pode:
- ✓ Fazer login
- ✓ Explorar a aplicação
- ✓ Contribuir com novas features (veja CONTRIBUTING.md)

---
