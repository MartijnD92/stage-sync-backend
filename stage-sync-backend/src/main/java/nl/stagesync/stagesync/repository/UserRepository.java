package nl.stagesync.stagesync.repository;

import nl.stagesync.stagesync.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
