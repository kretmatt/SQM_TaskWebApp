package at.technikumwien.taskwebapp.repositories;

import at.technikumwien.taskwebapp.entities.ToDo;
import at.technikumwien.taskwebapp.entities.ToDoState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByState(ToDoState state);
}
