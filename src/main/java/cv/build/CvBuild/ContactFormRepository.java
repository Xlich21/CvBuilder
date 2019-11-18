package cv.build.CvBuild;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("contactFormRepository")
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {

}
