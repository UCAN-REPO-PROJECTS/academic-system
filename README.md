Segue a versão atualizada do **README.md**, levando em conta que o sistema será desenvolvido em **Java** com **Spring Boot** e **PostgreSQL**, e incluirá a tabela de **Localidade**:  

---

# **Sistema Acadêmico**

Bem-vindo ao repositório do **Sistema Acadêmico**, um sistema completo para gerenciar a administração acadêmica de instituições de ensino.  

---

## **Descrição do Projeto**

O Sistema Acadêmico é uma solução robusta e escalável, projetada para gerenciar estudantes, professores, disciplinas, matrículas e avaliações, além de oferecer suporte a funcionalidades como gestão de precedências e emissão de relatórios.  

Este sistema será desenvolvido com as seguintes tecnologias:
- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** PostgreSQL

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

## **Estrutura do Banco de Dados**
O sistema inclui as seguintes tabelas principais:  
- **Estudante**: Gerencia informações dos alunos.
- **Curso**: Armazena os cursos oferecidos pela instituição.
- **Disciplina**: Registra as disciplinas disponíveis.
- **Localidade**: Representa cidades, estados ou países associados a estudantes, professores ou campi.
- **Matrícula**: Relaciona estudantes a cursos e disciplinas.
- **PlanoCurso**: Associa disciplinas ao semestre de um curso.
- **Precedência**: Registra pré-requisitos entre disciplinas.
- **Avaliação**: Armazena as notas dos estudantes.

---

## **Pré-requisitos**
Para executar o projeto localmente, você precisará de:
- **Java 17+**  
- **Maven** para gerenciar dependências  
- **PostgreSQL** como banco de dados  
- **Git** para controle de versão  

---

## **Como Executar o Projeto**

### **1. Clone o Repositório**
```bash
git clone https://github.com/usuario/sistema-academico.git
cd sistema-academico
```

### **2. Configure o Banco de Dados**
Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE sistema_academico;
```

Atualize as configurações no arquivo `application.properties` ou `application.yml`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_academico
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### **3. Compile o Projeto**
Compile e baixe as dependências do projeto com Maven:
```bash
mvn clean install
```

### **4. Execute o Sistema**
Inicie o servidor:
```bash
mvn spring-boot:run
```

O sistema estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## **Estrutura do Projeto**
```plaintext
sistema-academico/
│
├── src/main/java/
│   ├── com.example.sistemaacademico/
│       ├── controllers/     # Controladores das rotas REST
│       ├── models/          # Entidades e modelos de dados
│       ├── repositories/    # Interfaces para acesso ao banco de dados
│       ├── services/        # Regras de negócio
│       └── SistemaAcademicoApplication.java # Classe principal
│
├── src/main/resources/
│   ├── application.properties # Configurações do sistema
│   └── static/                # Arquivos estáticos (HTML, CSS, JS)
│
├── pom.xml            # Gerenciador de dependências Maven
└── README.md          # Documentação do projeto
```

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
