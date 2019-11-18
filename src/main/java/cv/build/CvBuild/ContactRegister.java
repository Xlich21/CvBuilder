package cv.build.CvBuild;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContactRegister {
    @Autowired
    JdbcTemplate jdbcTemplate;


    Logger logger = LoggerFactory.getLogger(ContactRegister.class);              //Logger.org.slf4j (pentru a aparea logarea in consola)

    public void saveContact (ContactForm contactForm) {

        logger.info("S-a primit spre salvare in BD user-ul: "+contactForm.toString());  //acest mesaj v-a aparea in consola
        Object[] params = new Object[4];
        params[0] = contactForm.getName();
        params[1] = contactForm.getEmail();
        params[2] = contactForm.getSubject();
        params[3] = contactForm.getMessage();

        jdbcTemplate.update("insert into user values (null,?,?,?,?);", params);

    }
}
