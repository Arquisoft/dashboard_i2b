package view;

import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import statisticsCalculator.ProposalsProcessor;

/**
 * Created by Jorge on 04/04/2017.
 */
@RestController
public class DataController {

    @Autowired
    private ProposalsProcessor propProc;

    @RequestMapping("/data/topVotes")
    @ResponseBody
    public Map<String,Object> getMostVotedProposals(){
        List<Proposal> proposals = propProc.getTopVotes();
        Map<String, Object> values = new HashMap<>();

        values.put("type","pie");
        Map<String, Object> subvalues = new HashMap<>();
        List<Integer> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(Proposal prop: proposals){
            data.add(prop.getVotes());
            labels.add(prop.getAuthor());
        }
        String[] backgroundColor ={"#F7464A",
                "#46BFBD",
                "#FDB45C",
                "#33FFF9",
                "#EB35FE"};
        subvalues.put("data",data);
        subvalues.put("backgroundColor",backgroundColor);
        values.put("datasets",subvalues);
        values.put("labels",labels);
        return values;
    }

}
