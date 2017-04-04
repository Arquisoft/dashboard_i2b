package dbmanagement;

import domain.Comment;
import domain.Proposal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */
@Repository
public interface CommentsRepository extends MongoRepository<Comment, ObjectId>{

    List<Comment> findByProposal(Proposal prop);
    Comment findByAuthorAndCreated(String author, Date created);

    long count();
}
