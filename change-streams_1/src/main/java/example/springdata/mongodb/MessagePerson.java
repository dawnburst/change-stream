package example.springdata.mongodb;

import lombok.Data;

@Data
public class MessagePerson {

    private Person personDocument;
    private String operationType;
    private Class targetType;

    public MessagePerson(Person personDocument, String operationType, Class targetType) {
        this.personDocument = personDocument;
        this.operationType = operationType;
        this.targetType = targetType;
    }
}
