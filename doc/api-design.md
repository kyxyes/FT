# Web API Design v1.1

**$HOST is the domain of this server**

HOST=http://ip:port/application

## User Management

###Data structure of User

```
{
	"id":
	"email":
	"password":
	"username":
}
```

### API for User

**1. sign up**

Request type: 

```
Request:
POST		$HOST/api/v1/user
{"email":"example1@example.com","password":"123456","username":"example"}

Response:
{"userid":3,"password":"123456","email":"example1@example.com","username":"example"}
```

**2. sign in**

```
Request:
GET		$HOST/api/v1/user/{email}

Response:
{"userid":3,"password":"123456","email":"example1@example.com","username":"example"}
```

**3. update user info**

```
Request:
PUT		$HOST/api/v1/user
{"userid":3,"email":"example1@example.com","password":"12345678","username":"example1"}

Response:
{"userid":3,"password":"12345678","email":"example1@example.com","username":"example1"}
```

## Category for Expense

###Data structure of Category

```
{
	"id":
	"name":
	"description":
}
```
 
### API for Category

**1. create category**

```
Request:
POST		$HOST/api/v1/category
{"name":"living","description":"happy hahahaha"}

Response:
{"categoryid":1,"name":"living","description":"happy hahahaha"}

Note:
The name of category should not duplicate.
```

**2. get all category**

```
Request:
GET		$HOST/api/v1/category

Response:
[{"categoryid":1,"name":"living","description":"happy hahahaha"},{"categoryid":2,"name":"Food","description":"So delicious"},{"categoryid":3,"name":"Clothes","description":"Dressing beautifully"}]
```

**3. update category info**

Request type: PUT

```
Request:
PUT		$HOST/api/v1/category
{"categoryid":1,"name":"living","description":"happy happy happy"}

Response:
{"categoryid":1,"name":"living","description":"happy happy happy"}
```

## Expense Entry

###Data structure

```
{
	"userid":
	"entryid":
	"amount":
	"date": date
	"categoryid":
	"location":
	"description": 
}
```
 
### API for Category

**1. create entry**

```
Request:
POST		$HOST/api/v1/{userid}/expense
{"userid":3,"amount":"5.43","date":"2015-11-11 13:18:22.495","categoryid":"2","location":"GWU Gelman Library","description":"StarBucks Coffee"}

Response:
{"userid":3,"amount":5.43,"date":"2015-11-11 13:18:22.495","categoryid":2,"location":"GWU Gelman Library","description":"StarBucks Coffee"}

Note:
There is no entryid returned here.
```

**2. get all entry of a user**

```
Request:
GET		$HOST/api/v1/{userid}/expense

Response:
[{"userid":3,"entryid":6,"amount":22.33,"date":"2015-11-09 02:18:39.495","categoryid":1,"location":"GWU Gelman Library","description":"StarBucks Coffee"},{"userid":3,"entryid":9,"amount":5.43,"date":"2015-11-11 13:18:22.495","categoryid":2,"location":"GWU Gelman Library","description":"StarBucks Coffee"}]
```

**3. update entry**

```
Request:
PUT		$HOST/api/v1/{userid}/expense
{"userid":3,"entryid":6,"amount":9.21,"date":"2015-11-09 02:18:39.495","categoryid":1,"location":"Rosslyn metro","description":"StarBucks Coffee, two cups"}

Response:
{"userid":3,"entryid":6,"amount":9.21,"date":"2015-11-09 02:18:39.495","categoryid":1,"location":"Rosslyn metro","description":"StarBucks Coffee, two cups"}
```


## Lookup expense analysis report -> Need to rephrase

Request type: GET

Request parameter:

```
	"userid":
	"begindate":
	"enddate":
	"reportby": hour / category
	"format": line / histogram / pie
```

Response :

```
[
	{
		"name":
		"value":
	}
	...
]
```


### API for Analysis

**1. get category percentage**

Request type: 


Request: (attention for the date format: should be the same as the one created in expense entry, "2015-11-09 02:18:39.495")

```
GET		$HOST/api/v1/{userid}/analysis/category/{startDate}/{endDate}
```

Response:

```
{
     "user_id": int
     "catogery_id": int
     "catogery_name": String
     "percentage" : double
}
```
