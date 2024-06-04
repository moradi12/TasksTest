package exam1.example.demo2.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String taskName;
    private String NameIncharge ;
    private boolean isComplete;
    private LocalDateTime dateOfExecution;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public void setComplete(boolean complete) {
    }
}
