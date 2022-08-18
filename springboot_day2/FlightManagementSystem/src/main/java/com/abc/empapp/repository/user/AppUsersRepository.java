package com.abc.empapp.repository.user;

import com.abc.empapp.domain.entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUsersRepository extends JpaRepository<AppUsers, Integer> 
{
	@Query("from AppUsers where username = :username")
	public AppUsers getUsersByUsername(String username);
}
