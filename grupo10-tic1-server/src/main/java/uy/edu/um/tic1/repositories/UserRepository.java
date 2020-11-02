package uy.edu.um.tic1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uy.edu.um.tic1.entities.users.AppUser;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Integer> {


    Optional<AppUser> findByUsername(String username);


}
