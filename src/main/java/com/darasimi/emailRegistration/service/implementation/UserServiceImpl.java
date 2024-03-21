package com.darasimi.emailRegistration.service.implementation;

import com.darasimi.emailRegistration.entity.Confirmation;
import com.darasimi.emailRegistration.entity.User;
import com.darasimi.emailRegistration.repository.ConfirmationRepository;
import com.darasimi.emailRegistration.repository.UserRepository;
import com.darasimi.emailRegistration.service.EmailService;
import com.darasimi.emailRegistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;
    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){throw new RuntimeException("Email already exists");}
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

//        Send email to user
        emailService.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendMimeMessageWithAttachments(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendMimeMessageWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());


        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
//        confirmationRepository.delete(confirmation);

        return Boolean.TRUE;
    }
}
