package at.technikumwien.taskwebapp;

import at.technikumwien.taskwebapp.entities.ToDo;
import at.technikumwien.taskwebapp.entities.ToDoState;
import at.technikumwien.taskwebapp.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;
@Configuration
@Profile("test")
public class TestDBInitializer {
    @Autowired
    private ToDoRepository toDoRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady(){
        toDoRepository.saveAll(
                List.of(
                        new ToDo("Description of ToDo1 - Test", "Test 1",  ToDoState.TODO, LocalDate.of(2021,12,13)),
                        new ToDo("Description of ToDo2 - Test", "Test 2",  ToDoState.DOING,LocalDate.of(2021,11,23)),
                        new ToDo("Description of ToDo3 - Test", "Test 3",  ToDoState.DONE,LocalDate.of(2021,8,13))
                )
        );
    }
}




