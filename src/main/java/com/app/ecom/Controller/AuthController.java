package com.app.ecom.Controller;

import com.app.ecom.DTO.AuthRequest;
import com.app.ecom.Util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String generateJWT(@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            return jwtUtil.generateToken(request.username());
        } catch (Exception e) {
            throw e;
        }
    }

}
