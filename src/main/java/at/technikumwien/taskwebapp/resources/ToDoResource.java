package at.technikumwien.taskwebapp.resources;

import at.technikumwien.taskwebapp.entities.ToDo;
import at.technikumwien.taskwebapp.entities.ToDoState;
import at.technikumwien.taskwebapp.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources/todos")
public class ToDoResource {
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/{id}")
    public ToDo retrieveToDo(@PathVariable long id){
        return toDoRepository.findById(id).get();
    }

    @GetMapping
    public List<ToDo> retrieveAllToDos(@RequestParam(defaultValue = "TODO") ToDoState state){
        return toDoRepository.findAllByState(state);
    }
}
