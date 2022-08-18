package com.abc.empapp.service.user;

import com.abc.empapp.domain.entity.AppUsers;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService extends UserDetailsService{
	AppUsers saveUsers(AppUsers appUsers);

	AppUsers getAllUsersByRole(String role);
}
