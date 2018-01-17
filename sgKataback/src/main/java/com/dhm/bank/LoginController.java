package com.dhm.bank;

import com.dhm.bank.clients.Client;
import com.dhm.bank.clients.ClientService;
import com.dhm.bank.envConfig.AppPropperties;
import com.dhm.bank.util.JwtTokenGenerator;
import com.dhm.bank.util.JwtTokenValidator;
import com.dhm.bank.util.JwtUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final ClientService clientService ;
    private final AppPropperties appProperties;
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    public LoginController(ClientService clientService, AppPropperties appProperties, JwtTokenValidator jwtTokenValidator, JwtTokenGenerator jwtTokenGenerator) {
        this.clientService = clientService;
        this.appProperties = appProperties;
        this.jwtTokenValidator = jwtTokenValidator;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    /**
     * Test les donnée d'utilisateur envoyer , s'ils sont valid un access token sera générer et qu'est
     * valid que pour une heure
     *
     * @param object contient le username , password et l'option remember me
     * @return le ou les deux token à stoker dans les cookies du browser
     */
    @PostMapping("")
    public @ResponseBody
    ResponseEntity<Object> login(@RequestBody Object object) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) object;
        String username = (String) linkedHashMap.get("username");
        String password = (String) linkedHashMap.get("password");
        Client client = clientService.getClientByUsername(username);
        if (client==null || !new BCryptPasswordEncoder().matches(password,client.getClientSecrets().getSecret())){
            return new ResponseEntity<>("wrong username or password ", HttpStatus.UNAUTHORIZED);
        }
        if (!client.getClientAccount().getActive()){
            return new ResponseEntity<>("your account is not active", HttpStatus.UNAUTHORIZED);
        }
        JwtUserDto jwtUserDto = new JwtUserDto();
        jwtUserDto.setId(client.getClientId());
        jwtUserDto.setUsername(username);
        String token =jwtTokenGenerator.generateToken(jwtUserDto);
        return new ResponseEntity<>(token, HttpStatus.UNAUTHORIZED);
    }

}
