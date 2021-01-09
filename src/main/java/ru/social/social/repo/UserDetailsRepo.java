package ru.social.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.social.social.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
