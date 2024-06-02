package zti.ztiproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing accounts.
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    /**
     * Retrieves an account by its ID.
     * @param id the account ID
     * @return an Optional containing the account, or empty if not found
     */
    Optional<Account> findById(String id);
}
