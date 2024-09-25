package nl.han.oose.project.sb3.service.impl;

import lombok.RequiredArgsConstructor;
import nl.han.oose.project.sb3.dao.UserDao;
import nl.han.oose.project.sb3.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserDao userDao;

    @Override
    public UserDetailsService userDetailsService()
    {
        return username -> userDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
