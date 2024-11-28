# **Sistema Acadêmico**

Bem-vindo ao repositório do **Sistema Acadêmico**, um sistema completo para gerenciar a administração acadêmica de instituições de ensino.  

---

## **Descrição do Projeto**

O Sistema Acadêmico é uma solução robusta e escalável, projetada para gerenciar estudantes, professores, disciplinas, matrículas e avaliações, além de oferecer suporte a funcionalidades como gestão de precedências e emissão de relatórios.  

Este sistema é desenvolvido com as seguintes tecnologias:
- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** PostgreSQL
- **Automação:** Makefile para tarefas comuns do ciclo de desenvolvimento

---

## **Funcionalidades Principais**
- **Gestão de Estudantes e Professores**: Cadastro e consulta de informações pessoais.
- **Gestão de Cursos e Disciplinas**: Administração de cursos, disciplinas e planos de ensino.
- **Matrículas e Avaliações**:
  - Matrícula de estudantes em cursos e disciplinas.
  - Lançamento e consulta de notas.
- **Precedências**:
  - Controle de disciplinas com pré-requisitos.
  - Consulta de disciplinas disponíveis para matrícula.
- **Portais Personalizados**:
  - **Estudante**: Acesso ao histórico acadêmico e notas.
  - **Professor**: Gerenciamento de turmas e lançamento de avaliações.
- **Gestão de Localidades**:
  - Registro de localidade de origem de estudantes, professores e campi.
- **Relatórios**: Geração de relatórios acadêmicos e administrativos.
- **Integração com PostgreSQL** para armazenamento seguro e escalável.

---

## **Pré-requisitos**
Para executar o projeto localmente, você precisará de:
- **Java 17+**  
- **Maven** para gerenciar dependências  
- **PostgreSQL** como banco de dados  
- **Docker** (opcional para gerenciar o banco de dados)  
- **Make** (opcional para automação de tarefas)  
- **Git** para controle de versão  

---

## **Como Executar o Projeto**

### **1. Clone o Repositório**
```bash
git clone https://github.com/UCAN-REPO-PROJECTS/sistema-academico.git
cd sistema-academico
```

### **2. Configure o Banco de Dados**
Se utilizar o Docker, inicie o banco de dados com:
```bash
make db-start
```

Para configurar manualmente:
1. Crie um banco de dados PostgreSQL:
   ```sql
   CREATE DATABASE sistema_academico;
   ```
2. Atualize as configurações no arquivo `application.properties` ou `application.yml`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_academico
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

### **3. Compile o Projeto**
Utilize o Maven para compilar o projeto:
```bash
make build
```

### **4. Execute o Sistema**
Inicie o servidor:
```bash
make run
```

O sistema estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## **Estrutura do Projeto**
```plaintext
sistema-academico/
│
├── src/main/java/
│   ├── com.example.sistemaacademico/
        ├── common/
        │    ├── exceptions/
        │    │    ├── EntityNotFoundException.java
        │    │    ├── ValidationException.java
        │    │    └── AccessDeniedException.java
        │    ├── response/
        │    │    ├── ApiResponse.java
        │    │    └── HttpStatusCode.java
        │    └── utils/
        │         └── CommonUtils.java
│       ├── controllers/     # Controladores das rotas REST
│       ├── entities/        # Entidades
│       ├── models/          # Modelos de dados
│       ├── repositories/    # Interfaces para acesso ao banco de dados
│       ├── services/        # Regras de negócio
│       └── SistemaAcademicoApplication.java # Classe principal
│
├── src/main/resources/
│   ├── application.properties # Configurações do sistema
│   └── static/                # Arquivos estáticos (HTML, CSS, JS)
│
├── scripts/
│   └── init.sql               # Script para inicialização do banco de dados
├── Makefile                   # Automação de tarefas
├── pom.xml                    # Gerenciador de dependências Maven
└── README.md                  # Documentação do projeto
```

---

## **Uso do Makefile**

O **Makefile** foi incluído para facilitar tarefas comuns no ciclo de desenvolvimento. Confira os comandos disponíveis:

### **Comandos Principais**
- `make build`:
  - Compila o projeto e gera o arquivo `.jar` na pasta `target/`.
- `make run`:
  - Compila e executa o aplicativo.
- `make clean`:
  - Remove todos os arquivos temporários e de build.
- `make test`:
  - Executa os testes automatizados do projeto.
- `make db-start`:
  - Inicia o banco de dados PostgreSQL em um contêiner Docker.
- `make db-stop`:
  - Para e remove o contêiner PostgreSQL.
- `make db-init`:
  - Executa o script `init.sql` para inicializar o banco de dados.
- `make db-status`:
  - Verifica o status do contêiner PostgreSQL.
- `make help`:
  - Lista todos os comandos disponíveis.

---

## **Contribuindo**
Contribuições são bem-vindas! Para colaborar:  
1. Faça um **fork** do projeto.  
2. Crie uma branch para sua feature ou correção:  
   ```bash
   git checkout -b minha-feature
   ```  
3. Faça commit das suas alterações:  
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```  
4. Envie para o repositório remoto:  
   ```bash
   git push origin minha-feature
   ```  
5. Abra um **Pull Request** explicando suas mudanças.  

---

## **Licença**
Este projeto está licenciado sob a [MIT License](LICENSE).  

---

## **Contato**
Se tiver dúvidas ou sugestões, entre em contato:
- **E-mail:** jopaulo142@gmail.com
- **GitHub:** [Jonathan Mandombe](https://github.com/m4nd0mb3)
