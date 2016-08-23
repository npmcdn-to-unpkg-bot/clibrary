package com.coller.clibrary;

import com.coller.clibrary.entity.*;
import com.coller.clibrary.repository.*;
import com.coller.clibrary.vo.AuthorityLevel;
import com.coller.clibrary.vo.EnableLevel;
import lombok.extern.slf4j.Slf4j;
import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Slf4j
public class Application {
    public static void main(String... args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ServletRegistrationBean h2ServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, AuthorityRepository authorityRepository,
                                    BookRepository bookRepository, AuthorRepository authorRepository,
                                    LogEntryRepository logEntryRepository, PasswordEncoder encoder){
        return (args) -> {
            saveUserAndAuthority("Jamie", AuthorityLevel.ROLE_ADMIN, userRepository, authorityRepository, encoder);
            saveUserAndAuthority("Jamie", AuthorityLevel.ROLE_USER, userRepository, authorityRepository, encoder);
            saveUserAndAuthority("Sarah", AuthorityLevel.ROLE_USER, userRepository, authorityRepository, encoder);
            createBooks(bookRepository, authorRepository, logEntryRepository);
        };
    }

    private void saveUserAndAuthority(String username, AuthorityLevel authorityLevel,
                                      UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder encoder) {
        User user = userRepository.findUserByUsername(username);
        if(null == user){
            String password = encoder.encode("password");
            userRepository.save(new User(username, password, EnableLevel.ENABLED));
        }
        Authority authority = authorityRepository.findAuthorityByUsernameAndAuthority(username, authorityLevel.name());
        if(null == authority){
            authorityRepository.save(new Authority(username, authorityLevel));
        }
    }

    private void createBooks(BookRepository bookRepository, AuthorRepository authorRepository, LogEntryRepository logEntryRepository){
        Book book1 = new Book("12345","978-0-470-41799-7", "JavaScript & AJAX For Dummies", "Learn JavaScript and Ajax quickly");
        //Book book2 = new Book("12346", "0-672-32879-8", "Sams Teach Yourself JavaScript in 24 Hours", "Learn JavaScript in 24 Chapters");

        book1 = bookRepository.save(book1);
        //book2 = bookRepository.save(book2);

        Author author1 = new Author("Andy", null, "Harris");
        //Author author2 = new Author("Michael", null, "Moncur");

        author1 = authorRepository.save(author1);
        //author2 = authorRepository.save(author2);

        book1.setAuthors(Arrays.asList(author1));
        //book2.setAuthors(Arrays.asList(author2));

        Keyword keyword1 = new Keyword("Java Script");
        Keyword keyword2 = new Keyword("AJAX");
        Keyword keyword3 = new Keyword("Programming");

        book1.setKeywords(Arrays.asList(keyword1, keyword2, keyword3));

        book1 = bookRepository.save(book1);
        //book2 = bookRepository.save(book2);

        LogEntry logEntry1 = new LogEntry(book1, "Good Book to learn JavaScript and AJAX together");
        //LogEntry logEntry2 = new LogEntry(book2, "Good Book to learn JavaScript");

        logEntry1 = logEntryRepository.save(logEntry1);
        //logEntry2 = logEntryRepository.save(logEntry2);

    }
}