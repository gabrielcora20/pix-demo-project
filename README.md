# Módulo de Pagamentos Pix

## **Descrição**

Com o objetivo de cadastrar e consultar registros referentes a pagamentos Pix, desenvolvi o atual projeto com o foco no processamento otimizado e confiável das informações. Em conjunto com os endpoints, foram implementadas ferramentas de observabilidade para prevenção de falhas inesperadas nos clusters.

### Serviços

A aplicação conta com os seguintes serviços:

- [Conjunto de endpoints para manipulação de pagamentos Pix](http://localhost:8080/swagger-ui/index.html) - Interface em Swagger para utilização dos endpoints.
- [Grafana](http://localhost:3000) - Local onde as métricas, dashboards e logs podem ser visualizados de forma centralizada.
- [Prometheus](http://localhost:9090) - Aqui é possível visualizar as métricas e alertas de _healthcheck_ (esses dados também podem ser visualizados no [Grafana](http://localhost:3000)).

### Tecnologias Utilizadas

- Código fonte escrito em [Kotlin](https://kotlinlang.org/) e [Spring Boot 3](https://spring.io/).
- Optei por desenvolver a solução em um banco de dados [MongoDB](https://www.mongodb.com/) por se tratar de apenas um domínio sem relacionamentos.
- Tratativa de Log utilizando [Grafana Loki](https://grafana.com/docs/loki/latest/).
- Desenvolvimento da solução em microserviços, diminuindo a chance de indisponibilidade.
- Arquitetura em Camadas (Application, Domain, Infra), utilizando o padrão CQRS, com o objetivo de separar as operações de escrita e leitura, corroborando para a elaboração de um código potencialmente escalável a longo prazo.
- Event Driven para o disparo de eventos nas alterações realizadas pelo sistema. Além de garantir tratativas especialistas para cada tipo distinto de evento.
- Orquestração dos microserviços implementada com load balancer em [NgInx](https://docs.nginx.com/nginx/admin-guide/load-balancer/http-load-balancer/) gerenciando qual dos quatro clusters deverá ser chamado.
- [Grafana Tempo](https://grafana.com/docs/tempo/latest/) para manipulação de dados de telemetria.
- [Open Telemetry Collector](https://opentelemetry.io/docs/collector/) para o recebimento dos dados de telemetria diretamente da aplicação e realização do envio ao [Grafana Tempo](https://grafana.com/docs/tempo/latest/)
- [Prometheus](http://localhost:9090) para registro de métricas e acompanhamento dos alertas.
- [Grafana](https://grafana.com/docs/grafana/latest/) para visualização das informações de observabilidade capturadas de todas as ferramentas ([Grafana Tempo](https://grafana.com/docs/tempo/latest/), [Prometheus](http://localhost:9090), [Grafana Loki](https://grafana.com/docs/loki/latest/))
- Contêineres gerenciandos via [Docker](https://docs.docker.com/) e [Docker Compose](https://docs.docker.com/compose/).

## **Instruções de Instalação**

### Pré-requisitos

- Ter o [Docker](https://docs.docker.com/get-docker/) e [Docker Compose](https://docs.docker.com/compose/install/) instalados.

### Executando o projeto

- Baixe o projeto
  ```sh
  git clone git@github.com:gabrielcora20/pix-demo-project.git
  ```
- Entre na pasta do projeto
  ```sh
  cd pix-demo-project
  ```
- Suba a aplicação no [Docker](https://docs.docker.com/)
  ```sh
  docker-compose up -d --build
  ```
