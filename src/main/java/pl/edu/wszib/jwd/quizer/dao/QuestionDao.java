package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.jwd.quizer.model.Question;

public interface QuestionDao extends CrudRepository<Question, Long> {
}
