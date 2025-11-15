# 🎵 Musiquinhas

Sistema de Música com **JavaFX**, **JPA/EclipseLink** e **MySQL**.

---

## ⚡ Quick Start

### Pré-requisitos
- **Java 21+** instalado ([Download](https://www.oracle.com/java/technologies/downloads/#java21))
- **Maven 3.8.9+** instalado ([Download](https://maven.apache.org/download.cgi))
- **MySQL Workbench** ou outro cliente MySQL instalado e configurado ([Download](https://dev.mysql.com/downloads/workbench/))

### Instruções de Setup

1. **Clone o repositório:**
   ```bash
   git clone <seu-repositorio>
   cd Musiquinhas
   ```
3. Configure o banco de dados MySQL

   Siga as instruções na seção "Sobre o MySQL" no fim desse arquivo para criar o banco de dados necessário.

2. **Compile e execute o projeto:**
   ````
   Execute Main.java com Java 21+ 
   ````
   Ou use o Maven: 
   ```bash
   mvn clean compile

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

## 🛠️ Tecnologias

| Componente | Versão |
|---|---|
| **Java** | 21 |
| **JavaFX** | 21.0.4 |
| **JPA/EclipseLink** | 4.0.8 |
| **Jakarta Persistence** | 3.2.0 |
| **MySQL Connector/J** | 8.4.0 |
| **Build Tool** | Maven 3.8.9+ |

---

## 📚 Recursos Úteis

- **Diagrama de Classes:** [Google Drive](https://drive.google.com/file/d/1RB2pSDZ7NxxXuDmupfmntSblDXcKu5tq/view?usp=sharing)
- **Design (Figma):** [Protótipo](https://www.figma.com/design/ZbL1iXkfnI5Q0tlUnxnjfz/Untitled?node-id=0-1&t=6itlZXmDYFiaCuYs-1)


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

## Sobre o MySQL

Crie o banco de dados `musiquinhas` no mySQL antes de rodar o projeto.

```sql
CREATE DATABASE musiquinhas;
```

> [!WARNING]
> Caso seu banco não rode na porta padrão (3306), ajuste a URL de conexão no arquivo `persistence.xml`. Também não esqueça de configurar o usuário e senha do banco nesse mesmo arquivo.

--- 