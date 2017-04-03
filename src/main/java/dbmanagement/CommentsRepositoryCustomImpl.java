package dbmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Damian on 03/04/2017.
 */
@Service
public class CommentsRepositoryCustomImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public CommentsRepositoryCustomImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }
}
