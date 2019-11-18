package cv.build.CvBuild;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact_form", schema = "alex_fenichiu")
public class ContactForm {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    @Size(min = 2, message = "required")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
//    @Size(min = 5, max = 100)
    @Column(name = "subject")
    private String subject;

    @NotNull
//    @Size(min = 5, max = 2000)
    @Column(name = "message")
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String toString() {
        return "Person(Name: " + this.name + ", Email: " + this.email + ", Subject: " + this.subject + ", Message:" + this.message + ")";
    }
}
