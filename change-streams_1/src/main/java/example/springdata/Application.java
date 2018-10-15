package example.springdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.springdata.mongodb.MessagePerson;
import example.springdata.mongodb.Person;
import example.springdata.mongodb.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Person("Shahar", "Zror", 38));
        repository.save(new Person("Chen", "Zror", 34));

        System.out.println("Persons findAll():");
        for (Person person: repository.findAll()
             ) {
//            System.out.println(person);

            ObjectMapper objectMapper = new ObjectMapper();

            MessagePerson messagePerson = new MessagePerson(person, "Insert" , Person.class);

            System.out.println(messagePerson.getPersonDocument().toString());


//            Person personVar = objectMapper.readValue(messagePerson.getFullDocument(), Person.class);

//            System.out.println(personVar.toString());
        }


    }
}
