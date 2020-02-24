# desafioCabal

### Criação(POST)

Há a necessidade de passar o objeto inteiro na requisição para que seja insirido o novo comercio.

Ex: 
{
   "nome":"Distribuidora de alimentos do Seu Joaquim S.A",
   "cnpj":20828705000109,
   "endereco":{
      "CEP":710908720,
      "logradouro":"Logradouro de teste",
      "bairro":"Sao vicente",
      "cidade":"Sao Paulo",
      "UF":"SP",
      "complemento":"N/A"
   },
   "telefones":[
      {
         "tipoTelefone":"RESIDENCIAL",
         "numero":"6198323333"
      }
   ],
   "emails":[
      {
         "email":"matheusfontinele2@gmail.com"
      }
   ]
}

### Alterar (PUT)
Para realizar a alteração, também há a necessidade de enviar o objeto inteiro, com o acressímo dos id's de cada entidade corretamente. Para isso, recomendo utilizar primeiro o serviço de busca por CNPJ e utilizar a resposta dele como requisição para a alteração com os dados desejados.

Ex de reposta da pesquisa por CNPJ:

[
  {
    "nome":"Distribuidora de Salgados do Seu Joaquim S.A",
    "cnpj":77832672000198,
    "endereco":{
      "id":77832672000198,
      "logradouro":"Logradouro de teste",
      "bairro":"Sao vicente",
      "cidade":"Sao Paulo",
      "complemento":"N/A",
      "uf":null,
      "cep":0
    },
    "telefones":[
      {
        "id":3,
        "tipoTelefone":"RESIDENCIAL",
        "numero":"6198323333"
      }],
    "emails":[
      {
        "id":3,
        "email":"matheusfontinele2@gmail.com"
      }]
  },
  {
    "nome":"Distribuidora de alimentos do Seu Joaquim S.A",
    "cnpj":99227585000144,
    "endereco":{
      "id":99227585000144,
      "logradouro":"Logradouro de teste",
      "bairro":"Sao vicente",
      "cidade":"Sao Paulo",
      "complemento":"N/A",
      "uf":null,
      "cep":0
    },
    "telefones":[
      {
        "id":1,
        "tipoTelefone":"RESIDENCIAL",
        "numero":"6198323333"
      }],
    "emails":[
      {
        "id":1,
        "email":"matheusfontinele2@gmail.com"
      }]
  }]

  Assim, garante que as chaves (id's) sempre serão enviados corretamente. 