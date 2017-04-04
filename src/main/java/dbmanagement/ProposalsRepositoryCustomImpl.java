package dbmanagement;

import dbmanagement.Agrupations.ProposalCommented;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge on 03/04/2017.
 */
@Service
public class ProposalsRepositoryCustomImpl implements ProposalsRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ProposalsRepositoryCustomImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    @Override
    public List<ProposalCommented> getProposalsMostCommented() {
        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("comments").exists(true)),
                Aggregation.project("title").and("comments").size().as("amountComments"),
                Aggregation.sort(Sort.Direction.DESC, "amountComments"),
                Aggregation.limit(5));

        //Defining the aggregation (agregation, input,output)
        AggregationResults<ProposalCommented> groupParticipants =
                mongoTemplate.aggregate(agg, Proposal.class, ProposalCommented.class);

        return groupParticipants.getMappedResults();
    }
}
