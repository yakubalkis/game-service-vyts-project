package com.game.server.rest;

import com.game.server.entity.Level;
import com.game.server.entity.Rank;
import com.game.server.entity.User;
import com.game.server.exception.DuplicatedUserInfoException;
import com.game.server.request.EmailRequest;
import com.game.server.request.SmsRequest;
import com.game.server.rest.dto.AuthResponse;
import com.game.server.rest.dto.LoginRequest;
import com.game.server.rest.dto.SignUpRequest;
import com.game.server.rest.dto.SignUpResponse;
import com.game.server.security.JwtTokenProvider;
import com.game.server.security.WebSecurityConfig;
import com.game.server.security.oauth2.AuthProvider;
import com.game.server.service.LevelService;
import com.game.server.service.RankService;
import com.game.server.service.UserService;
import com.game.server.service.EmailService;
import com.game.server.service.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final EmailService emailService;
    private final SmsService smsService;
    private final LevelService levelService;
    private final RankService rankService;
    private final AuthenticationConfiguration authConfiguration;


    @Autowired
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @PostMapping("/authenticate")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponse(token);
    }


    @PostMapping("/login")
    public SignUpResponse loginLocal(@RequestBody LoginRequest userRequest) throws Exception {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());

        Authentication auth = authenticationManager().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = tokenProvider.generate(auth);
        User user = userService.getUserByUsername(userRequest.getUsername());
        EmailRequest emailRequest = new EmailRequest("s68472968@gmail.com", user.getEmail(), "Giriş Yapma İşlemi Başarılı",
                "Başarıyla giriş yaptınız!");
        SmsRequest smsRequest = new SmsRequest (user.getPhoneNumber(), "Başarıyla giriş yaptınız!");
        // set jwt and username to response
        SignUpResponse authResponse = new SignUpResponse();
        authResponse.setMessage("Bearer " + jwtToken);
        authResponse.setUsername(user.getUsername());
        authResponse.setRole(user.getRole());

        sendMail(emailRequest);
        sendSms(smsRequest);

        return authResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUsername()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s already been used", signUpRequest.getEmail()));
        }

        userService.saveUser(mapSignUpRequestToUser(signUpRequest));

        String token = authenticateAndGetToken(signUpRequest.getUsername(), signUpRequest.getPassword());
        return new AuthResponse(token);
    }

    @PostMapping("/register/{roleName}")
    public ResponseEntity<SignUpResponse> registerLocal(@PathVariable String roleName, @RequestBody User user) {

        SignUpResponse authResponse = new SignUpResponse();
        String role = null;
        EmailRequest emailRequest = new EmailRequest("s68472968@gmail.com", user.getEmail(), "Kayıt Olma İşlemi Başarılı",
                "Başarıyla kayıt oldunuz!");
        SmsRequest smsRequest = new SmsRequest (user.getPhoneNumber(), "Başarıyla kayıt oldunuz!");
        // check if username is already existed
        if (userService.getUserByUsername(user.getUsername()) != null) {
            authResponse.setMessage("Username already in use");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        // set role to laborant
        if (roleName.equals("admin")) {
            role = "ROLE_ADMIN";
        } else if (roleName.equals("user")) {
            role = "ROLE_USER";
        }
        user.setRole(role);
        user.setProvider(AuthProvider.LOCAL);

        // set level and rank as init
        Rank rank = rankService.findByRankName("Newbie");
        Level level = levelService.findByLevelName("1");
        user.setRank(rank);
        user.setLevel(level);

        // encode and set password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        authResponse.setMessage("Successfully registered. You can login.");

        sendMail(emailRequest);
        sendSms(smsRequest);

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    private String authenticateAndGetToken(String username, String password) throws Exception {
        Authentication authentication = authenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(WebSecurityConfig.USER);
        user.setProvider(AuthProvider.LOCAL);
        return user;
    }

    public String sendMail(EmailRequest emailrequest) {
        return emailService.sendMail(emailrequest.getTo(), emailrequest.getCc(), emailrequest.getSubject(), emailrequest.getBody());
    }

    public void sendSms(SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }
}
