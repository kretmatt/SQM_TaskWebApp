package at.technikumwien.taskwebapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, length = 50)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ToDoState state;

    @Column(nullable = false)
    private LocalDate dueDate;

    public ToDo(String description, String title, ToDoState state, LocalDate dueDate){
        this(null, description, title, state, dueDate);
    }

    @JsonIgnore
    public String getToDoContent(){
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is null or blank");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is null or blank");
        }

        return title + " :" + description;
    }

}
