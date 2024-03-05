# StockMaster
Enzo farias - rm98792
Beatriz Lucas - RM99104


API do projeto StockMaster - Plataforma de controle de estoque para loja de varejo

## Documentação

### Endpoints

- [Cadastrar Usuario](#cadastrar-usuario)
- [Realizar Login](#realizar-login)
- [Listar Categorias de Produtos](#listar-categorias-de-produtos)
- [Listar Produtos](#listar-produtos)
- [Registrar Venda](#registrar-venda)

---

### Cadastrar Usuario

`POST` /usuario

Cadastrar um novo usuário ao acessar a plataforma.

#### Corpo da Requisição

| campo          | tipo   | obrigatório | descrição                       |
|:--------------:|:------:|:-----------:|:-------------------------------:|
| `nome_completo`| string | ✅          | Nome completo do usuário.       |
| `email`        | string | ✅          | Email para login e contato.     |
| `senha`        | string | ✅          | Senha para acessar a plataforma.|

```json
{
    "nome_completo": "João Silva",
    "email": "joao.silva@email.com",
    "senha": "segredo123"
}
Exemplo de resposta
json
Copy code
{
    "id_usuario": 1,
    "nome_completo": "João Silva",
    "email": "joao.silva@email.com",
    "senha": "segredo123"
}
Código de Status
código	descrição
201	Usuário cadastrado com sucesso
400	Validação falhou. Verifique as regras para o corpo da requisição
Realizar Login
POST /usuario/login

Autentica o usuário na plataforma de controle de estoque.

Corpo da Requisição
campo	tipo	obrigatório	descrição
email	string	✅	Email para login e contato.
senha	string	✅	Senha para acessar a plataforma.
json
Copy code
{
    "email": "joao.silva@email.com",
    "senha": "segredo123"
}
Exemplo de resposta
json
Copy code
{
    "id_login": 1,
    "email": "joao.silva@email.com",
    "senha": "segredo123"
}
Código de Status
código	descrição
200	Autenticação bem-sucedida.
401	Você passou credenciais inválidas.
Listar Categorias de Produtos
GET /categoria/produto

Retorna um array com todas as categorias de produtos.

Exemplo de resposta
json
Copy code
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
Código de Status
código	descrição
200	Categorias retornadas com sucesso
401	Usuário não autenticado. Realize autenticação em /usuario/login
Listar Produtos
GET /categoria/{id}/produto

Lista todos os produtos de uma categoria específica.

Exemplo de resposta
json
Copy code
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
    // ... outros produtos
]
Código de Status
código	descrição
200	Produtos retornados com sucesso
401	Usuário não autenticado. Realize autenticação em /usuario/login
404	Não existe produto com o id informado. Consulte a lista em /produto
Registrar Venda
POST /venda

Permite registrar a venda de um ou mais produtos na plataforma.

Corpo da Requisição
campo	tipo	obrigatório	descrição
id_usuario	int	✅	ID do usuário que está realizando a venda.
produtos	array	✅	Array de produtos contendo id_produto e quantidade.
total_venda	double	✅	Valor total da venda.
json
Copy code
{
    "id_usuario": 1,
    "produtos": [
        {
            "id_produto": 1,
            "quantidade": 2
        },
        {
            "id_produto": 2,
            "quantidade": 1
        }
    ],
    "total_venda": 1079.97
}
Exemplo de resposta
json
Copy code
{
    "id_venda": 1,
    "id_usuario": 1,
    "produtos": [
        {
            "id_produto": 1,
            "quantidade": 2
        },
        {
            "id_produto": 2,
            "quantidade": 1
        }
    ],
    "total_venda": 1079.97
}
Código de Status
código	descrição
201	Venda registrada com sucesso
400	Validação falhou. Verifique as regras para o corpo da requisição
401	Usuário não autenticado. Realize autenticação em /usuario/login
404	Não existe um ou mais dos ids fornecidos. Consulte em /usuario e /produto