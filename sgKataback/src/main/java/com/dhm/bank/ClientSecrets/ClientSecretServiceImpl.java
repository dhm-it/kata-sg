package com.dhm.bank.ClientSecrets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class ClientSecretServiceImpl implements ClientSecretService {
    private final ClientSecretRepository clientSecretRepository ;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random random = new SecureRandom();
    @Autowired
    public ClientSecretServiceImpl(ClientSecretRepository clientSecretRepository) {
        this.clientSecretRepository = clientSecretRepository;
    }

    @Override
    public String generateSecret() {
        return randomKey(20,10,"");
    }

    @Override
    public ClientSecrets getSecretById(String id) {
        return clientSecretRepository.findOne(id);
    }

    @Override
    public ClientSecrets getSecretBySecret(String secret) {
        return clientSecretRepository.findBySecret(secret);
    }

    @Override
    public void saveSecret(ClientSecrets clientSecrets) {
        clientSecretRepository.save(clientSecrets);
    }


    /**
     * Generate random char with secureRandom
     *
     * @return a random char
     */
    char randomChar() {
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));
    }

    /**
     * Generate a unique key for each user which will be used as a token
     *
     * @param length
     * @param spacing
     * @param spacerChar
     * @return a random key
     */
    String randomKey(int length, int spacing, String spacerChar) {
        int charSize = length;
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while (charSize > 0) {
            if (spacer == spacing) {
                sb.append(spacerChar);
                spacer = 0;
            }
            charSize--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
