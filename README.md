# Micro-Service-with-RESTfull-and-GraphQL-WebServices
A Spring Boot project for Bank Accounts management, using Spring Web, Spring Data JPA, H2, and Lombok... The steps included creating a RESTful web service tested with Postman, Swagger documentation, RESTful API with Spring Data Rest, DTOs and Mappers and a GraphQL web service



so the first entity of the Micro Service is 'BankAccount' 
this is how it looks from the h2 console
![Screenshot (1655)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/21dbf049-352b-4c8b-9474-833a69e4c694)


to create new bank accounts, we only need to insert the balance, the currency and the type of the account
here is an example of testing the method of creating accounts with PostMan
![Screenshot (1656)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/82842cae-13a9-4ed1-9d2d-08bf3b0de7e7)


using swagger, we tested the method that displays all the accounts
![Screenshot (1657)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/2ab87349-be8a-4d2f-9668-bd5987895ad1)


now testing the method that gets you the account when given its id
![Screenshot (1658)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/982d9fcc-3149-4b14-aed5-821b5fcfdb30)


now to add a new account, we may provide all the info of an account but none of it is imperative
![Screenshot (1659)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/e38903c4-1b21-4279-9b01-9f4d7b8721cf)
![Screenshot (1660)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/062c65fe-fe65-4e21-a8ac-82079fb315aa)


another approach we can take to create a rest controller having all the basic features (but still not the best approach) is to use Spring Data Rest and its projections
the following example illustrates the use of a projection to get all the accounts that has "CURRENT_ACCOUNT" as a type
![Screenshot (1661)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/76f4cba7-c917-444e-ab1d-dabc112c12be)


now if we re being practical, the accounts IDs are automaticaly generated.. therefore they won t appear when entering data for new accounts
the use of DTOs is meant to deal precisely with this kind of situation
![Screenshot (1662)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/b03a491f-02a1-47e1-9e2d-15c5c127b139)


regarding the update, we don t want to allow users to alter ids nor creation date, so we should keep their original values before the update
![Screenshot (1663)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/f481a1c1-3c62-42ec-ae9b-da947001da8c)


after completing the essential features of 'BankAccounts' with Rest, now we re about to do the same thing using a GraphQL controller to manage 'BankAccounts'
by using graphQl, we can choose to display all the attributes from a certain query, or also displaying only some specific fields
![Screenshot (1664)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/553442ed-e57a-41a9-8535-071c1c14c41b)
![Screenshot (1665)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/631958be-da06-463f-9ab1-64c0c77031e8)


this method allows me to refer to an account through its id
![Screenshot (1666)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/e59d4635-a464-405b-8740-ab3f0df3d62a)


if we enter an Id that does not exist, we'll receive a general exception from GraphQL and not our exception that we handled at the business layer
so to deal with exceptions in GraphQL, we should create a class that extends DataFetcherExceptionResolverAdapter and override the 'resolveToSingleError()' method in  order to get the exception  message we provided in our business layar
otherwise GraphQL gives its own Exception message by default
![Screenshot (1667)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/a714f7e3-40e8-4ec7-a346-5e486de6aa44)


to test the addBankAccount method, the user should provide the data needed as described in the 'BankAccountDTO' (which is an input type in the graphqls file)
in this example, we have specified that the created account should have these values.. after that, we displayed some data of this newly added account
![Screenshot (1669)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/83d2954c-be74-44a8-967c-6982c3803c62)


regarding both types of queries (Query and Mutation), it is always best to use parameterized queries, in order to make them easier for further use either with Angular (for example) or something else
![Screenshot (1670)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/02342329-8226-463e-9fd2-bb07e81d345d)


here we tested the updateAccount method, it takes two params, first the id of the target account to alter, second is the object that has the new data
and a quick display of some of its attributes
![Screenshot (1671)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/021d1960-4dab-4184-b989-1401829c301a)


since our deleteAccount method returns a boolean, if the object was in deed deleted we ll have 'true' as a result (which is the case in our example here)
otherwise, if there was no account having that id, it shall return 'false'
![Screenshot (1672)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/04f00f5e-f36b-4519-a7c2-ad172fce0550)


to make things a bit more interesting, we ve added a second entity "Customer" that has an attribute of the previously created entity "BankAccount"
this way we ll be in a position to test bidirectional relationships between entities
![Screenshot (1673)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/60f918af-5c7b-4301-8a40-64e632186507)
![Screenshot (1674)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/22991cb8-78cc-4103-87c9-2446c1ffa16d)


unlike Rest, GraphQL has no cyclic dependencies issues when asked to return data from an object that itself has data inside the first object.
this count as one of the great pros of GraphQL
![Screenshot (1675)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/13464c0b-a3b9-440c-967d-f9da0a515cd7)


displaying our customers, for each one we want to get their basic info + their list of accounts (10 each)
![Screenshot (1676)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/a1534b2a-aeb8-49c9-9292-78f7c78fbb30)


mean  while in REST land, the reason why we got this horror is because REST can t handle bidirectional relationships (poor Rest! Rest In Peace!)
but there are actually some ways to deal with this kind of errors, maybe the best one is to use DTOs, use @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) which is basically saying that we don t care about displaying this attribute, we only want to write
![Screenshot (1677)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/a1fa0e04-42df-41ed-aebb-07ae7d7cd337)


here is how it looks after the use of "@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)" instruction 
![Screenshot (1678)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/479f9b91-d89d-4488-a8a9-d7a56e7857f6)


here is what we got in "Customers" after introcucing DTOs 
![Screenshot (1679)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/c9a96717-c661-470d-ba98-dc029bfab93c)


for obvious reasons.. it is best to get rid of tha data regarding the "customer" inside of the "bankAccounts"
![Screenshot (1680)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/901eb11c-2dd4-4b01-97d5-44a08e5e2a9a)


regarding the creation of new customers it is beneficial to keep the required items to a min level, here in our example only one attribute is needed, namely the name
![Screenshot (1681)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/2f0734c3-429a-4cf7-92fe-655417227e46)


to be able to delete a customer we have to go through deleting all his accounts first. 
only after making sure all of his accounts are deleted, only then we can proceed with the elemination of the whole customer
![Screenshot (1682)](https://github.com/YassineAlami/Micro-Service-with-RESTfull-and-GraphQL-WebServices/assets/40896739/6654fe80-bdf5-427c-b2a2-5670709097f8)

