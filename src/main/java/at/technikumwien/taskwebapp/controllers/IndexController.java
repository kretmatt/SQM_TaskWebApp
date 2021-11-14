package at.technikumwien.taskwebapp.controllers;

import at.technikumwien.taskwebapp.entities.ToDoState;
import at.technikumwien.taskwebapp.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private ToDoRepository toDoRepository;

    @RequestMapping
    public String index(@RequestParam(defaultValue = "DONE")ToDoState state, Model model){
        var todos = toDoRepository.findAllByState(state);

        model.addAttribute("todos",todos);
        model.addAttribute("state",state);

        return "index";
    }
}
