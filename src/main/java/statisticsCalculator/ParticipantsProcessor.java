package statisticsCalculator;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.ParticipantsRepository;
import dbmanagement.ParticipantsRepositoryCustom;
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
    private ParticipantsRepository dat;

    @Autowired
    private ParticipantsRepositoryCustom datCust;

    private Long amount;
    //private Map<String,Long> ageAgrupation;
    private List<ParticipantLocalization> nationAgrup;

    //In order to personalize the ageAgrupation ranges
    //private int[] limits = {0,17,25,35,50,70,90};

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<ParticipantLocalization> getNationAgrup() {
        return nationAgrup;
    }

    @Autowired
    public ParticipantsProcessor(ParticipantsRepository dat, ParticipantsRepositoryCustom datCust){
        this.dat=dat;
        amount= dat.count();
        nationAgrup = datCust.getParticipantsGroupByNationality();
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
    /*
    Ideas:
        Amount of registered Users
        Amount of users per nationality
        Amount of users by ranges of age
        Users with the same name (useless for Council)

     */


}
