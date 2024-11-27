# Variáveis
APP_NAME = sistema-academico
JAR_FILE = target/$(APP_NAME).jar
DB_CONTAINER_NAME = postgres-sistema-academico
DB_PORT = 5432
DB_USER = seu_usuario
DB_PASSWORD = sua_senha
DB_NAME = sistema_academico
SPRING_PROFILE = dev

# Comandos principais
.PHONY: all build dev run clean test db-start db-stop db-init

all: build

# Compilar o projeto com Maven
build:
	mvn clean package

# Executar o aplicativo Spring Boot no modo development
dev:
	mvn spring-boot:run

# Executar o aplicativo Spring Boot
run: build
	java -jar $(JAR_FILE) --spring.profiles.active=$(SPRING_PROFILE)

# Limpar arquivos temporários e de build
clean:
	mvn clean

# Executar testes
test:
	mvn test

# Iniciar o banco de dados PostgreSQL com Docker
db-start:
	docker run --name $(DB_CONTAINER_NAME) \
		-e POSTGRES_USER=$(DB_USER) \
		-e POSTGRES_PASSWORD=$(DB_PASSWORD) \
		-e POSTGRES_DB=$(DB_NAME) \
		-p $(DB_PORT):5432 \
		-d postgres:latest

# Parar o banco de dados PostgreSQL
db-stop:
	docker stop $(DB_CONTAINER_NAME) && docker rm $(DB_CONTAINER_NAME)

# Inicializar o banco de dados com scripts SQL
db-init:
	psql -h localhost -U $(DB_USER) -d $(DB_NAME) -f scripts/init.sql

# Verificar o status do banco de dados
db-status:
	docker ps -f name=$(DB_CONTAINER_NAME)

# Ajuda: Exibir os comandos disponíveis
help:
	@echo "Comandos disponíveis:"
	@echo "  build       - Compilar o projeto"
	@echo "  run         - Executar o aplicativo Spring Boot"
	@echo "  clean       - Limpar arquivos temporários"
	@echo "  test        - Executar os testes do projeto"
	@echo "  db-start    - Iniciar o banco de dados PostgreSQL com Docker"
	@echo "  db-stop     - Parar o banco de dados PostgreSQL"
	@echo "  db-init     - Inicializar o banco de dados com scripts SQL"
	@echo "  db-status   - Verificar o status do banco de dados"
