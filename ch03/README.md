## BaseRepository
- expose only what you need and not all the CRUD or JPA functionalities
- **@NoRepositoryBean** ensures that the BaseRepository interface is excluded  from the proxy object creation
- since the signatures match that of CrudRepository, Spring Data routes the runtime calls to the actual JPA
- implementation class