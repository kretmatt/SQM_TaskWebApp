package at.technikumwien.taskwebapp;

import at.technikumwien.taskwebapp.entities.ToDo;
import at.technikumwien.taskwebapp.entities.ToDoState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToDoTest {
    private ToDo toDo;

    @BeforeEach
    public void setUp(){
        toDo = new ToDo("Description of ToDo1 - Test", "Test 1",  ToDoState.TODO, LocalDate.of(2021,12,13));
    }

    @Test
    public void getToDoContent(){
        assertEquals(toDo.getTitle()+" :"+toDo.getDescription(),toDo.getToDoContent());
    }

    @Test
    public void testGetToDoContentWithTitleNull(){
        toDo.setTitle(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> toDo.getToDoContent()
        );
    }

    @Test
    public void testGetToDoContentWithDescriptionNull(){
        toDo.setDescription(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> toDo.getToDoContent()
        );
    }

    @Test
    public void testGetToDoContentWithTitleBlank(){
        toDo.setTitle(" ");

        assertThrows(
                IllegalArgumentException.class,
                () -> toDo.getToDoContent()
        );
    }

    @Test
    public void testGetToDoContentWithDescriptionBlank(){
        toDo.setDescription(" ");

        assertThrows(
                IllegalArgumentException.class,
                () -> toDo.getToDoContent()
        );
    }
}
