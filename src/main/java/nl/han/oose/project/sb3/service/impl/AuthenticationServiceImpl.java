package nl.han.oose.project.sb3.service.impl;

import nl.han.oose.project.sb3.dao.UserDao;
import nl.han.oose.project.sb3.domain.request.SignUpRequest;
import nl.han.oose.project.sb3.domain.request.SigninRequest;
import nl.han.oose.project.sb3.domain.response.JwtAuthenticationResponse;
import nl.han.oose.project.sb3.entities.Role;
import nl.han.oose.project.sb3.entities.User;
import nl.han.oose.project.sb3.service.AuthenticationService;
import nl.han.oose.project.sb3.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService
{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request)
    {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userDao.save(user);
        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userDao.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
