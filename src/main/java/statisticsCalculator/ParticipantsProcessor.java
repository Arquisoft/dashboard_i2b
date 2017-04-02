package statisticsCalculator;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.ParticipantsRepository;
import dbmanagement.ParticipantsRepositoryCustom;
import dbmanagement.ParticipantsRepositoryCustomImpl;
import domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class ParticipantsProcessor implements Processor{

    @Autowired
    private ParticipantsRepository dat;

    @Autowired
    private ParticipantsRepositoryCustom datCust;

    //public Map<String, List<Double>> statistics = new HashMap<String, List<Double>>();
    //public Map<String, Object> statistics = new HashMap<String, Object>();
    // Esto fuerza a un cast quizas mejor pensar otra cosa...

    public Long amount;
    //private Map<String,Long> ageAgrupation;
    public List<ParticipantLocalization> nationAgrup;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    //Quizas es mejor cambiar la estructura de forma que cad método devuelva
    //lo que tiene que devolver y se use en el dashboard,
    //Sino el usuario tiene que saber como castear las cosas.
    @Override
    public void Update() {
        //Updates all the info
        amount++;
    }

    public void Update(Participant participant){
        amount++;
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


    @Autowired
    public ParticipantsProcessor(ParticipantsRepository dat, ParticipantsRepositoryCustomImpl datCust){
        this.dat=dat;
        amount= dat.count();
        nationAgrup = datCust.getParticipantsGroupByNationality();

    }

    /*
    Ideas:
        Amount of registered Users
        Amount of users per nationality
        Amount of users by ranges of age
        Users with the same name (useless for Council)

     */

    /*public void calculateTotalUsers(){
        int amount=0;
        //Consulta para conseguir dato
        statistics.put("totalUsers",amount);
    }

    public void calculateUsersByRange(){
        //Quizas demasiado complicada
    }

    public void calculateUsersByNationality(){
        Map<String,Long> agrupation = new HashMap<String,Long>();
        //Consulta para conseguir dato
        statistics.put("UsersByNationality",agrupation);
    }
    */


}
