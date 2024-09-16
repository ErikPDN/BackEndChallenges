package br.com.erikchallenges.todolist.repository;

import br.com.erikchallenges.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
