package backend;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name= "indices")
public class Indices{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @NotEmpty
    private Integer militaryIndex;
    
    @NotEmpty
    private Integer economicIndex;
    
    @NotEmpty
    private Integer civilUnrest;

    public long getId() {
	return id;
    }

    public Integer getMilitaryIndex() {
	return militaryIndex;
    }

    public Integer getEconomicIndex() {
	return economicIndex;
    }

    public Integer getCivilUnrest() {
	return civilUnrest;
    }
}
