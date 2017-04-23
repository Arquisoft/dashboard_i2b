package dbmanagement;

import domain.Participant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */
@Repository
public interface ParticipantsRepository extends MongoRepository<Participant, ObjectId>{

    long count();

}
