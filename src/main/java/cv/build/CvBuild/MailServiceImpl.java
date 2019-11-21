package cv.build.CvBuild;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;

    @Override
    public void send(String fromAddress, String toAddress, String subject, String content) throws Exception {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(toAddress);
            helper.setFrom(fromAddress);
            helper.setSubject(subject);
            helper.setText(content);
            sender.send(mimeMessage);

        }

    }

