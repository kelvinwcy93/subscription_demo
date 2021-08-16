# subscription_demo
Start up Springboot application by executing com.subs.demo.SubscriptionDemoApplication

Endpoint : http://localhost:8080/generateInvoiceDate

POST request param:

DAILY

{
    "amount":123.12,
    "type":"DAILY",
    "startDate":"17/07/2021",
    "endDate":"17/09/2022"
}

WEEKLY

{
    "amount":123.12,
    "type":"WEEKLY",
    "day":"TUESDAY",
    "startDate":"17/07/2021",
    "endDate":"17/09/2022"
}


MONTHLY

{
    "amount":123.12,
    "type":"MONTHLY",
    "day":"1",
    "startDate":"17/07/2021",
    "endDate":"17/09/2022"
}
