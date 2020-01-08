package backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@Controller
public class IndicesController {
    private final long id = 0;
    private Integer militaryIndex;
    private Integer economicIndex;
    private Integer civilUnrest;

    @Autowired
    IndicesRepository indicesRepository;

    private final Logger logger = LoggerFactory.getLogger(IndicesRepository.class);
    @GetMapping("/indices/new")
    public String initCreationForm(Map<String, Object> model) {
	logger.info("Entered into Controller Layer");
	Indices indices = new Indices();
	model.put("indices", indices);
	return "indices/createOrUpdateIndicesForm";
    }

    @PostMapping("/indices/new")
    public String processCreationForn(@Valid Indices indices, BindingResult result){
	if (result.hasErrors()){
	    return "Indices/createOrUpdateIndicesForm";
	}else{
	    indicesRepository.save(indices);
	    return "redirect:/indices";
	}
    }
	

    @GetMapping("/owners")
    public String getAllIndices(Map<String, Object> model) {

	logger.info("Entered into Controller Layer");
	Collection<Indices> results = indicesRepository.findAll();
	model.put("selections",results);
	return"Indices/indicesList";
    }
    
    @GetMapping("/Indices/{indicesId}")
    public String findIndicesByID(@PathVariable("indicesId") int id,Map<String,Object> model){
	logger.info("Entered into Controller Layer");
	Collection<Indices>results = indicesRepository.findById(id);
	model.put("selections", results);
	return "indices/indicesList";
    }

    @GetMapping("/owners/find")
    public String findOwner(Map<String,Object> model){
	model.put("indices", new Indices());
	return"indices/findIndices";
    }

}

