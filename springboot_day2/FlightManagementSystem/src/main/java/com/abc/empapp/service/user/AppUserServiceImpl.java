package com.abc.empapp.service.user;

import com.abc.empapp.domain.entity.AppUsers;
import com.abc.empapp.repository.user.AppUsersRepository;
import com.abc.empapp.security.jwt.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserServiceImpl implements AppUserService{

	@Autowired
	private AppUsersRepository userRepo;

	@Override  // from UserDetailsService
	public UserDetails loadUserByUsername(String username) throws 
	      UsernameNotFoundException {
		
		AppUsers user =  userRepo.getUsersByUsername(username);
		System.out.println(" ");
		System.out.println("--------Inside App User Service IMP ---------- ");
		System.out.println(" Arg :- "+username);
		System.out.println(" From Database "+user);
		
		return new MyUserDetails(user);
		
		
		
		//User user1 = new User("raja","abc",Arrays.asList(new SimpleGrantedAuthority("admin")));
		//return user1;
	}
	
	@Override
	public AppUsers saveUsers(AppUsers appUsers)
	{
		return userRepo.save(appUsers);
	}

	@Override
	public AppUsers getAllUsersByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}


