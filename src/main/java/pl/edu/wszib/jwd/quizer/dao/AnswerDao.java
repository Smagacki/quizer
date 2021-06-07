package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.jwd.quizer.model.Answer;

public interface AnswerDao extends CrudRepository<Answer, Long> {
}
