package example.springdata.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByFirstname(String firstName);

}
