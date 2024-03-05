# StockMaster

API do projeto StockMaster - Plataforma de controle de estoque para loja de varejo.

## Documentação

### Endpoints

- [Cadastrar Usuário](#cadastrar-usuário)
- [Realizar Login](#realizar-login)
- [Listar Categorias de Produtos](#listar-categorias-de-produtos)
- [Listar Produtos](#listar-produtos)
- [Registrar Venda](#registrar-venda)

---

### Cadastrar Usuário

`POST` /usuario

Cadastra um novo usuário na plataforma.

#### Corpo da Requisição

| Campo           | Tipo   | Obrigatório | Descrição                        |
|-----------------|--------|-------------|----------------------------------|
| `nome_completo` | string | ✅           | Nome completo do usuário.        |
| `email`         | string | ✅           | Email para login e contato.      |
| `senha`         | string | ✅           | Senha para acessar a plataforma. |

```json
{
  "nome_completo": "João Silva",
  "email": "joao.silva@email.com",
  "senha": "segredo123"
}

#### Exemplo de Resposta
json
{
  "id_usuario": 1,
  "nome_completo": "João Silva",
  "email": "joao.silva@email.com",
  "senha": "segredo123"
}

|Código |  	Descrição
|201    | 	Usuário cadastrado com sucesso.
|400    |	Validação falhou. Verifique as regras.

#### Realizar Login

POST /usuario/login

Autentica um usuário na plataforma.

#### Corpo da Requisição
Campo	Tipo	Obrigatório	Descrição
email	string	✅	Email para login.
senha	string	✅	Senha para acessar a conta.
json
{
  "email": "joao.silva@email.com",
  "senha": "segredo123"
}
Exemplo de Resposta
json
{
  "id_login": 1,
  "email": "joao.silva@email.com",
  "senha": "segredo123"
}

#### Código de Status
| código | descrição
|---     | ---
| `200`  | Autenticação bem-sucedida.
| `401`  | Você passou credenciais inválidas.


#### Listar Categorias de Produtos
GET /categoria/produto

Retorna todas as categorias de produtos disponíveis.

#### Exemplo de resposta
json
[
  {
    "id_categoria": 1,
    "nome": "Eletrônicos",
    "icone": "smartphone"
  },
  {
    "id_categoria": 2,
    "nome": "Vestuário",
    "icone": "tshirt"
  },
  {
    "id_categoria": 3,
    "nome": "Alimentos",
    "icone": "apple-alt"
  }
]
#### Código de Status

| código | descrição
|---     | ---
| `200`  | Categorias retornadas com sucesso
| `401`  | Usuário não autenticado. Realize autenticação em /usuario/login

#### Listar Produtos
GET /categoria/{id}/produto

Lista todos os produtos de uma categoria específica.

#### Exemplo de resposta
json
[
  {
    "id_produto": 1,
    "nome": "Smartphone XYZ",
    "descricao": "Último modelo da marca XYZ com 128GB de memória",
    "preco": 999.99,
    "quantidade_estoque": 20
  },
  {
    "id_produto": 2,
    "nome": "Jeans Masculino",
    "descricao": "Jeans azul masculino, tamanho 42",
    "preco": 79.99,
    "quantidade_estoque": 50
  }
  // outros produtos
]

#### Código de Status

| código | descrição
|---     | ---
| `200`  | Produtos retornados com sucesso
| `401`  | Usuário não autenticado. Realize autenticação em /usuario/login
| `404`  | Não existe Produto com o `id` informado. Consulte a lista em /Produto

#### Registrar Venda
POST /produto/{id}/vender

Registra a venda de um produto.

#### Corpo da Requisição

| campo | tipo | obrigatório | descrição
|:---:|:---:|:---:|:---:|
|id_usuario|	int	|✅|	ID do usuário que realiza a venda.
|id_produto|	int	|✅|	ID do produto vendido.
|quantidade|	int	|✅|	Quantidade do produto vendido.
|preco_total|	decimal	|✅|	Preço total da venda.

json
{
  "id_usuario": 1,
  "id_produto": 2,
  "quantidade": 3,
  "preco_total": 239.97
}

#### Exemplo de resposta
json
{
  "id_venda": 1,
  "id_usuario": 1,
  "id_produto": 2,
  "quantidade": 3,
  "preco_total": 239.97
}

| código | descrição
|---     | ---
|201    |	Venda registrada com sucesso.
|400    |	Validação falhou. Verifique as regras.
|401    |	Usuário não autenticado.
|404    |	Produto não encontrado.