package zti.ztiproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing games.
 */
public interface GamesRepository extends MongoRepository<Game, String> {
}
