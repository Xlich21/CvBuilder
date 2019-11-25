package cv.build.CvBuild;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ContactValidatorController  {


    @InitBinder
    public void initBinder (WebDataBinder binder){
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    public String finalString = null;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ContactFormRepository contactFormRepository;

    @Autowired
    private MailService mailService;

    @PostMapping(value = "savecontact-action")
    public String addContact (@ModelAttribute @Valid ContactForm contactForm, BindingResult bindingResult, Model model,
                                                         @RequestParam("name") String name,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("subject") String subject,
                                                         @RequestParam("message") String message) throws Exception {



        if (bindingResult.hasErrors()){
            System.out.println("Binding result has errors");
            return "contact";
        } else {

            try {
                 try {
                String contentName = "Name: " + contactForm.getName();
                String contentEmail = "Email: " + contactForm.getEmail();
                String contentSubject = "Subject: " + contactForm.getSubject();
                String contentMessage = "Message: " + contactForm.getMessage();
                String content = contentName + "\n" + contentEmail + "\n" +contentSubject + "\n" + contentMessage;
                mailService.send(contactForm.getEmail(), "alex.fenichiu@gmail.com", contactForm.getSubject(), content);
            } catch (Exception e){
                e.printStackTrace();
                return "Error Message";
            }

            contactForm.setName(name);
            contactForm.setEmail(email);
            contactForm.setSubject(subject);
            contactForm.setMessage(message);
            contactFormRepository.save(contactForm);
            List<ContactForm> contactFormList = contactFormRepository.findAll();
            model.addAttribute("contact_form", contactFormList);
            model.addAttribute("contactForm", contactForm);


        }
        return "savecontact";
    }
    @GetMapping(value = "savecontact")
    public String saveContact (@ModelAttribute ContactForm contactForm, Model model){
        model.addAttribute("contactForm", contactForm);

        return "savecontact";
    }

    @GetMapping("contact")
    public String viewTheForm( Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);

        List<ContactForm> contactFormList = contactFormRepository.findAll();
        model.addAttribute("contact_form", contactFormList);


        return "contact";
    }

    public String checkNullString(String str) {
        String endString = null;
        if (str == null || str.isEmpty()) {
            System.out.println("yes it is empty");
            str = null;
            Optional<String> opt = Optional.ofNullable(str);
            endString = opt.orElse("None");
            System.out.println("endString : " + endString);
        } else {
            ; //do nothing
        }


        return endString;
    }
}

