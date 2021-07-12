package app.cryptoarticlereader.repos;

import app.cryptoarticlereader.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepo extends CrudRepository<Word, Long> {
    List<Word> findAll();
}
