# Sistema de Consolidação de Salários - Desafio Técnico ESIG

Este projeto foi desenvolvido como parte de um desafio técnico para a vaga de Pessoa Desenvolvedora Java na ESIG Group. O objetivo foi construir uma aplicação web utilizando **JSF (Jakarta Server Faces)** que consolida e exibe os salários de pessoas com base em dados fornecidos em uma planilha.

---

## 🧩 Objetivo

Criar uma aplicação capaz de:
- Importar os dados fornecidos em uma planilha `.xlsx`
- Consolidar os salários de cada pessoa a partir de diferentes tabelas de apoio (`cargo`, `vencimentos`, `cargo_vencimentos`)
- Exibir a lista de pessoas com nome, cargo e salário final calculado
- Permitir o recálculo dos salários diretamente pela interface

---

## 🧠 Como funciona o cálculo de salário

A lógica de cálculo considera:
- Vencimentos do tipo `CREDITO` são **somados**
- Vencimentos do tipo `DEBITO` são **subtraídos**
- O resultado é consolidado e armazenado em uma tabela específica: `pessoa_salario_consolidado`

A consulta é feita com um `JOIN` entre as tabelas principais e executada diretamente via SQL nativo no banco de dados PostgreSQL.

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Jakarta EE (CDI, JPA)
- JSF (Jakarta Server Faces)
- PostgreSQL
- IntelliJ IDEA
- SmartTomcat
- JDBC / Hibernate
- HTML e Facelets

---

## 📁 Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── com.vitor/
│   │       ├── bean/               # Gerenciadores de tela (backing beans)
│   │       ├── dao/                # Camada de persistência (DAO)
│   │       ├── model/              # Entidades JPA
│   │       └── service/            # Serviços auxiliares (ex: teste de conexão)
│   ├── resources/
│   │   └── META-INF/persistence.xml
│   └── webapp/
│       ├── WEB-INF/
│       └── listagem.xhtml         # Interface JSF com tabela e botão de cálculo
