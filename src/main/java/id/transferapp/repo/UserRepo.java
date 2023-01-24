package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import id.transferapp.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
