package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.jwd.quizer.model.Question;
import pl.edu.wszib.jwd.quizer.model.User;

public interface QuestionDao extends CrudRepository<Question, Long> {
}
