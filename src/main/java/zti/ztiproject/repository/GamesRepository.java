package zti.ztiproject.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface GamesRepository extends MongoRepository<Game, String> {
}
