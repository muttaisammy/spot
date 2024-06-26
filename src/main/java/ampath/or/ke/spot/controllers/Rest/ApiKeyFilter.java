package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.LabResultsService;
import ampath.or.ke.spot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    @Autowired
    public UserService userService;
    private static final String API_KEY_HEADER = "X-API-KEY";
    //private static final String VALID_API_KEY = "your-api-key";

   // @Value("${api.key}")
    private String validApiKey="891b4b17feab99f3ff7e5b5d04ccc5da7aa96da6";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader(API_KEY_HEADER);

       // List<User> userList = userService.getAllUsers();

      /*  for( int x =0; x<userList.size();x++){
            String xx = userList.get(x).getUuid();
            System.out.println("xx"+ xx);
        } */

        String array[] = new String[] {
                "Toyota", "Mercedes", "BMW", "Volkswagen", "Skoda" };

        if (apiKey == null || !apiKey.equals(validApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API Key");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
