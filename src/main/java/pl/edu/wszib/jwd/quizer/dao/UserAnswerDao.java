package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.jwd.quizer.model.UserAnswer;

public interface UserAnswerDao extends CrudRepository<UserAnswer, Long> {
}
