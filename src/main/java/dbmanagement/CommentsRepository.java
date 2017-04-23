package dbmanagement;

import domain.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */
@Repository
public interface CommentsRepository extends MongoRepository<Comment, ObjectId>{

    Comment findByAuthorAndCreated(String author, Date created);

    long count();
}
