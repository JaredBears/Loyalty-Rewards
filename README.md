# Loyalty Rewards
Loyalty Rewards is an API that will track reward point accruals and redemptions, as well as the affiliated payer for each transaction.

## Installation
Download and run the  [JAR](https://github.com/JaredBears/Loyalty-Rewards/releases) file to run Loyalty Rewards.

## Usage
There are currently two methods of interacting with the program: Through the Swagger UI, and through URL Parameters.

To access the Swagger UI, after running the program, open your browser and proceed to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

### Reward Accrual
*Note*: At this time, the only valid payers are DANNON, UNILEVER, MILLER_COORS

To accrue new reward points, fill out the form labelled "POST loyalty/earned" in Swagger, or submit the information through URL parameters as follows:
```
http://localhost:8080/loyalty/earned?points=100&payerName=DANNON&date=2022-01-02%2001%3A02%3A03
```

### Reward Redemption
To accrue new reward points, fill out the form labelled "POST loyalty/spent" in Swagger, or submit the information through URL parameters as follows:
```
http://localhost:8080/loyalty/spent?points=50&date=2022-01-02%2001%3A02%3A04
```

### Check Transaction History
To view the full transaction history for the selected user, submit the Swagger form labelled "GET /loyalty/transactions" or browse to the URL:
```
http://localhost:8080/loyalty/transactions
```

### Check Point Balance
To view the current point balance for the selected user, submit the Swagger form labelled "GET /loyalty/points" or browse to the URL:
```
http://localhost:8080/loyalty/points
```

### Check Point Balance by Payer
To see how many points the current user has broken down by source of payer, submit the Swagger form labelled "GET /loyalty/points_payer" or browse to the URL:
```
http://localhost:8080/loyalty/points_payer
```

### Exit Program
*Note*: Exiting the program will delete all memory.

To exit the program, submit the Swagger form labelled "GET /loyalty/exit" or browse to the URL:
```
http://localhost:8080/loyalty/exit
```
