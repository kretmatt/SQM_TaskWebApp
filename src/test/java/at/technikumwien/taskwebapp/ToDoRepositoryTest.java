package at.technikumwien.taskwebapp;

import at.technikumwien.taskwebapp.entities.ToDoState;
import at.technikumwien.taskwebapp.repositories.ToDoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Tag("extended")
@Transactional
public class ToDoRepositoryTest {
    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    public void findAllByState(){
        var todos = toDoRepository.findAllByState(ToDoState.TODO);

        assertEquals(1,todos.size());
    }
}
