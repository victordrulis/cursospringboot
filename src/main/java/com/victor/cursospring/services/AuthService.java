package com.victor.cursospring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.victor.cursospring.domain.Cliente;
import com.victor.cursospring.repositories.ClienteRepository;
import com.victor.cursospring.services.exceptions.ObjectNotFoundException;

public class AuthService {
    
    @Autowired
    private ClienteRepository clienteRepo;
    
    @Autowired
    private BCryptPasswordEncoder pe;
    
    @Autowired
    private EmailService emailService;
    
    private Random rand;
    
    public void sendNewPassword(String email) {
        
        Cliente cliente = clienteRepo.findByEmail(email);
        
        if(cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }
        
        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));
        
        clienteRepo.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        
        for(int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if(opt == 0) {
            return (char) (rand.nextInt(10) + 48);
        } else if(opt == 1) {
            return (char) (rand.nextInt(26) + 65);
        } else {
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
