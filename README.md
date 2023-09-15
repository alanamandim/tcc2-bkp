Solicitações

### Criando solicitacao:
```sh
curl --request POST \
  --url http://localhost:8080/adicionaSolicitacao \
  --header 'Content-Type: application/json' \
  --data '{
	"motivo" :  "rancho",
	"destino": "BAFL",
	"viatura" : 1,
	"motorista": "6791212"
}'
```

### Criando inspeção:

```sh
curl --request POST \
  --url http://localhost:8080/adicionaInspecao \
  --header 'Content-Type: application/json' \
  --data '{
	"oleo" :  true,
	"pneu": true,
	"agua": true,
	"amassado" : false,
	"arranhado": false,
	"tanque" : "TresQuartos",
	"obs": " "
}'
``

### Aprovando solicitacao
```sh
curl --request PUT \
  --url http://localhost:8080/modificaStatusSolicitacao \
  --header 'Content-Type: application/json' \
  --data '{
	"aprovador": "6791212",
	"status": "Aprovada",
	"id":  "2023-08-10 10:21:30.616000"
}'

```
### Lista Solicitacao Aprovada
```sh
curl --request GET \
  --url http://localhost:8080/listaSolicitacaoAprovada
 ```

### Lista Solicitacao AguardandoAprovacao
```sh
curl --request GET \
  --url http://localhost:8080/listaSolicitacaoAguardandoAprovacao
  ```
