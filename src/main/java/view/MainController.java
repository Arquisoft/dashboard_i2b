package view;


import com.esotericsoftware.minlog.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import statisticsCalculator.CommentsProcessor;
import statisticsCalculator.ParticipantsProcessor;
import statisticsCalculator.ProposalsProcessor;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    //References to the different processors.
    //MEJOR HACERLOS SINGLETONS SI LOS HACEMOS ASÍ
    @Autowired
    private ParticipantsProcessor parProc;
    @Autowired
    private CommentsProcessor comProc;
    @Autowired
    private ProposalsProcessor propProc;

    @RequestMapping("/")
    public String landing(Model model) {
        return "dashboard";
    }

    @ModelAttribute("part")
    public ParticipantsProcessor getParProc() {
        return parProc;
    }

    @ModelAttribute("comm")
    public CommentsProcessor getComProc() {
        return comProc;
    }

    @ModelAttribute("prop")
    public ProposalsProcessor getPropProc() {
        return propProc;
    }

    @PatchMapping("/")
    public String update(Model model){
        Log.info("Patch request received, updating...");
        model.asMap().replace("prop", propProc);
        model.asMap().replace("comm", comProc);
        model.asMap().replace("part", parProc);
        return "dashboard";
    }
}