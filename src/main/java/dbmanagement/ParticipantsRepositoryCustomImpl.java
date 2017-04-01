package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;
import domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 01/04/2017.
 */
@Service
public class ParticipantsRepositoryCustomImpl implements ParticipantsRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    public ParticipantsRepositoryCustomImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }


    public List<ParticipantLocalization> getParticipantsGroupByNationality2() {
        Aggregation agg = Aggregation.newAggregation(Aggregation.group("nationality").count().as("amount"),
                                                        Aggregation.project("nationality").and("amount").previousOperation(),
                                                        Aggregation.sort(Sort.Direction.DESC, "amount"));

        //Defining the aggregation (agregation, input,output)
        AggregationResults<ParticipantLocalization> groupParticipants = mongoTemplate.aggregate(agg, Participant.class,ParticipantLocalization.class);

        List<ParticipantLocalization> result = groupParticipants.getMappedResults();

        return result;
    }

    @Override
    public List<ParticipantLocalization> getParticipantsGroupByNationality() {
        GroupByResults<ParticipantLocalization> results = mongoTemplate.group("users", GroupBy.key("nationality").initialDocument("{ amount: 0}").reduceFunction("function(doc,prev) {prev.amount +=1 }"),ParticipantLocalization.class);
        List<ParticipantLocalization> resultingList = new ArrayList<ParticipantLocalization>();
        //There is no other way to turn a GroupByResults
        for(ParticipantLocalization partLoc : results){
            resultingList.add(partLoc);
        }

        return resultingList;
    }
}
