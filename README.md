# Desafio de Android (Peixe Urbano)

## Estrutura do Projeto

O projeto está divido em 4 módulos principais:

### presentation

Utiliza o padrão MVVM para desacoplar a lógica de apresentação da view. A view representada por um fragmento é atualizada pelo LiveData.

### domain

Onde é definidos a lógica de negócio. Possui uma classe abstrata que deve rodar o caso de uso numa thread em background e atualiza a UI na main thread. Além disso, nessa camada está definida a entidade que deve compor as nossas regras de negócio.

### data

Implementa o padrão repositório e utiliza da internet para buscar os dados e o BD para cache. Existem dataclasses para internet (network/remote) e banco de dados (database/local) que devem utilizar das extensões definidas em /mapper antes de retornas os dados para a camada /domain.

### di

Além disso, existe um outro módulo (/di) que é responsável por injetar as dependência utilizando o framework Koin.
