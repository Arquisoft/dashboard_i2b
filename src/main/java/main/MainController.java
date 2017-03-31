package main;


import domain.Comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import statisticsCalculator.CommentsProcessor;
import statisticsCalculator.ParticipantsProcessor;
import statisticsCalculator.ProposalsProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());


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

}