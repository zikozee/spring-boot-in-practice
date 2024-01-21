## BaseRepository
- expose only what you need and not all the CRUD or JPA functionalities
- **@NoRepositoryBean** ensures that the BaseRepository interface is excluded  from the proxy object creation
- since the signatures match that of CrudRepository, Spring Data routes the runtime calls to the actual JPA
- implementation class

## Criteria
- CriteriaBuilder instance to create any of the CriteriaQuery, CriteriaUpdate, CriteriaDelete instances based on your need. 
- CriteriaQuery provides you with the functionalities to construct a query. 
- The CriteriaUpdate and CriteriaDelete allow you to define queries to perform bulk updates and deletes, respectively