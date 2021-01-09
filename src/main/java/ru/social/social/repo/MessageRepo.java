package ru.social.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.social.social.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
