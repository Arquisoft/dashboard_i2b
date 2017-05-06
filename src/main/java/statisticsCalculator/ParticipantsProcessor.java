package statisticsCalculator;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Database;
import domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class ParticipantsProcessor{

    @Autowired
    private Database dat;
    private Long amount;
    private List<ParticipantLocalization> nationAgrup;


    @Autowired
    public ParticipantsProcessor(Database dat){
        this.dat=dat;
        amount= dat.countParticipants();
        nationAgrup = dat.getParticipantsGroupByNationality();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<ParticipantLocalization> getNationAgrup() {
        return nationAgrup;
    }

    public void update(Participant participant){
        amount++;
        updateNationAgroup(participant);
    }

    private void updateNationAgroup(Participant participant){
        boolean found=false;
        String nationality = participant.getNationality();
        for(ParticipantLocalization partLoc : nationAgrup){
            if(nationality.equals(partLoc.getNationality())){
                partLoc.setAmount(partLoc.getAmount()+1);
                found=true;
            }
        }
        if(!found){
            nationAgrup.add(new ParticipantLocalization(nationality,1));
        }
    }
}
