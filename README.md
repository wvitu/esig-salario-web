# Sistema de Consolidação de Salários - Desafio Técnico ESIG

Este projeto foi desenvolvido como parte de um desafio técnico para a vaga de Pessoa Desenvolvedora Java na **ESIG Group**. O objetivo principal é consolidar dados salariais de pessoas a partir de múltiplas tabelas relacionais e exibi-los em uma aplicação web JSF.

---

## 🧩 Objetivo

- Importar os dados de uma planilha Excel com informações de pessoas, cargos e vencimentos
- Calcular o salário final de cada pessoa com base em créditos e débitos
- Exibir uma tela com listagem dos salários consolidados
- Permitir o recálculo dos salários via botão na interface

---

## 🧠 Lógica de cálculo

A tabela `pessoa_salario_consolidado` é preenchida com base nas seguintes regras:
- Cada vencimento do tipo **CREDITO** é somado ao salário da pessoa
- Cada vencimento do tipo **DEBITO** é subtraído
- O resultado final é agrupado por pessoa e armazenado

```sql
SUM(CASE WHEN v.tipo = 'CREDITO' THEN v.valor ELSE -v.valor END)
```

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Jakarta EE (CDI, JPA)
- JSF (Jakarta Server Faces)
- PostgreSQL
- Hibernate (JPA Provider)
- HTML/CSS
- Maven
- SmartTomcat
- IntelliJ IDEA

---

## 📁 Estrutura do projeto

```
esig-salario-web/
├── bean/               # JSF Backing Beans
├── dao/                # Data Access Object (JPA)
├── model/              # Entidades mapeadas com JPA
├── repository/         # (Opcional) Estrutura para futura extensão com Spring Data
├── service/            # Serviços auxiliares
├── webapp/
│   ├── listagem.xhtml  # Tela principal com botão e tabela
│   └── WEB-INF/
│       └── web.xml     # Configuração JSF
└── resources/
    └── META-INF/
        └── persistence.xml
```

---

## ▶️ Como rodar o projeto localmente

1. Clone o repositório:

```bash
git clone https://github.com/wvitu/esig-salario-web.git
cd esig-salario-web
```

2. Importe o projeto no **IntelliJ IDEA** como projeto Maven

3. Verifique o arquivo `persistence.xml`:
   - Atualize os dados de conexão com o PostgreSQL

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/esig_salario"/>
<property name="jakarta.persistence.jdbc.user" value="postgres"/>
<property name="jakarta.persistence.jdbc.password" value="sua_senha"/>
```

4. Execute o projeto usando o plugin **SmartTomcat**

5. Acesse no navegador:

```
http://localhost:8080/esig-salario-web/listagem.xhtml
```

---

## 💡 Funcionalidades

- Cálculo automático de salários com base nas tabelas `pessoa`, `cargo`, `vencimentos` e `cargo_vencimentos`
- Interface web com listagem dos dados consolidados
- Botão de **Recalcular Salários** executando a lógica de forma limpa
- Integração direta com banco de dados via JPA + SQL nativo

---

## 🖼️ Captura de tela

![Listagem de salários](lista-de-pessoas-salarios.png)

---

## 📦 Repositório

[https://github.com/wvitu/esig-salario-web](https://github.com/wvitu/esig-salario-web)

---

## ✍️ Autor

Desenvolvido por **Wanderson Vitor** como parte de uma avaliação técnica prática.  
Este projeto foi pensado para refletir boas práticas em Java Web com foco em clareza, separação de responsabilidades e fácil evolução futura.

📅 19/07/2025

---

## 📜 Licença

Este projeto é de uso educacional para fins de avaliação técnica. Direitos reservados ao autor.
