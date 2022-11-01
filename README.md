
# Java Code Challenge: Bank System

Design and create a Spring Boot RESTful web service for bank system that will contain multiple user accounts and will be able to perform transactions. The amounts are always in dollars and use 2 decimals. You would need to create the following classes: Bank, Account and Transaction.

## Account:
- account id
- name of the user
- account balance (it should not go below zero)

## Transaction:
Transaction represents an amount transfer from one to another account.
- amount
- originating account id
- resulting account id
- transaction reason

## Bank:
The bank charges a fee for each transaction, either a flat fee (e.g. $10) or percent fee (e.g. 5%)
that need to be defined. The bank system should support both transaction types.

- bank name
- list of accounts
- total transaction fee amount
- total transfer amount
- transaction flat fee amount
- transaction percent fee value

#### The user should be able to:
1. Create a bank with all required values
2. Create an account
3. Perform both flat fee and percent fee transaction from one account to another
4. See list of transactions for any account
5. Check account balance for any account
6. See list of bank accounts
7. Check bank total transaction fee amount
8. Check bank total transfer amount

### Notes:
- Make use of OOP principles: abstract classes, inheritance, overriding/overloading methods
- Make use of exceptions and handling
- Return proper error messages (e.g. account not found, not enough funds)
- Make sure to use the proper http status codes for the error messages-Readme file with instructions how the application works and can be started
- Push the code to GitHub
- Use a database to store and read the data
- Bonus points for basic user registration and user authentication with Spring Security and JWT

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DB_HOST`

`DB_PORT`

`DB_SCHEMA`

`DB_USERNAME`

`DB_PASSWORD`

## API Reference

#### Build & Run Docker containers
- `cd bank-system`
- `./gradlew clean build`
- `docker-compose up --build -d`

##### To access RESTful APIs, you will need to authenticate with default *user:* `admin` and *password:* `password`

Request:
```http
POST /auth/token

{
  "username": "string",
  "password": "string"
}
```

Response:
```http
200 OK

{
  "username": "string",
  "token": "string"
}
```
and then use this `token` from response to access RESTful APIs!

#### 1. Create a Bank

Request:
```http
POST /api/banks

{
  "name": "string",
  "transactionFlatFeeAmount": 0,
  "transactionPercentFeeValue": 0
}
```
Response:
```http
201 CREATED

{
  "id": "string",
  "name": "string",
  "totalTransactionFeeAmount": 0,
  "totalTransferAmount": 0,
  "transactionFlatFeeAmount": 0,
  "transactionPercentFeeValue": 0,
  "accounts": [
      {
          "id": "string",
          "user": "string",
          "balance": 0
      }
  ]
}
```

#### 2. Create an Account

Request:
```http
POST /api/banks/{name}/accounts

{
  "user": "string",
  "balance": 0
}
```
| Parameter | Type     | Description                             |
| :-------- | :------- | :-------------------------------------- |
| `name`    | `string` | **Required**. Name of the bank to fetch |

Response:
```http
201 CREATED

{
  "id": "string",
  "user": "string",
  "balance": 0
}
```

#### 3. Perform flat or percent fee Transaction

Request:
```http
POST /api/banks/{name}/transactions

{
  "amount": 0,
  "accountIdFrom": "string",
  "accountIdTo": "string",
  "reason": "string"
}
```
| Parameter | Type            | Description                                                            |
| :-------- | :---------------| :--------------------------------------------------------------------- |
| `name`    | `string` (path) | **Required**. Name of the bank to fetch                                |
| `fee-type`| `string` (query)| **Required**. Fee type to perform transaction, values: FLAT or PERCENT |


Response:
```http
201 CREATED

{
  "id": "string",
  "user": "string",
  "balance": 0
}
```

#### 4. Get all transactions for any Account

Request:
```http
  GET /api/banks/{name}/transactions
```

| Parameter | Type     | Description                              |
| :-------- | :------- | :--------------------------------------- |
| `name`    | `string` | **Required**. Name of the Bank to fetch  |
| `id`      | `string` | **Required**. Id of the Account to fetch |


Response:
```http
200 OK

[
  {
    "id": "string",
    "amount": 0,
    "from": "string",
    "to": "string",
    "reason": "string"
  },
  ...
]
```

#### 5. Get Account balance

Request:
```http
  GET /api/banks/{name}/accounts/{id}/balance
```

| Parameter | Type     | Description                              |
| :-------- | :------- | :--------------------------------------- |
| `name`    | `string` | **Required**. Name of the Bank to fetch  |
| `id`      | `string` | **Required**. Id of the Account to fetch |


Response:
```http
200 OK

{
  "user": "string",
  "balance": 0
}
```

#### 6. Get all Accounts

Request:
```http
  GET /api/banks/{name}/accounts
```

| Parameter | Type     | Description                              |
| :-------- | :------- | :--------------------------------------- |
| `name`    | `string` | **Required**. Name of the Bank to fetch  |

Response:
```http
200 OK

[
  {
    "id": "string",
    "user": "string",
    "balance": 0
  },
  ...
]
```

#### 7. Get total transaction fee amount

Request:
```http
  GET /api/banks/{name}/transactions/total_fee
```

| Parameter | Type     | Description                              |
| :-------- | :------- | :--------------------------------------- |
| `name`    | `string` | **Required**. Name of the Bank to fetch  |

Response:
```http
200 OK

{
  "totalTransactionFeeAmount": 0
}
```

#### 8. Get total transfer fee amount

Request:
```http
  GET /api/banks/{name}/transactions/total_transfer
```

| Parameter | Type     | Description                              |
| :-------- | :------- | :--------------------------------------- |
| `name`    | `string` | **Required**. Name of the Bank to fetch  |

Response:
```http
200 OK

{
  "totalTransferAmount": 0
}
```


## Authors

- [@sahitberisha](https://github.com/SahitBerisha)
