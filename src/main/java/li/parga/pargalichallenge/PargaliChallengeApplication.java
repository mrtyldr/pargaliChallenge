package li.parga.pargalichallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableSwagger2
@EnableWebSecurity
public class PargaliChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PargaliChallengeApplication.class, args);
    }
//    @Configuration
//    public class SpringFoxConfig {
//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .select()
//                    .apis(RequestHandlerSelectors.any())
//                    .paths(PathSelectors.any())
//                    .build();
//        }
//    }


}
