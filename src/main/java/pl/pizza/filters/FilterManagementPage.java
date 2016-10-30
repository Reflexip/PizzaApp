package pl.pizza.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pl.pizza.entity.users.User;
import pl.pizza.services.SignInManager;


@WebFilter(filterName = "FilterManagementPage", urlPatterns = {"/management.xhtml"})
public class FilterManagementPage implements Filter {
    
    private static final boolean debug = true;
 
    private FilterConfig filterConfig = null;
    
    private SignInManager signInManager;

    public FilterManagementPage() {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        signInManager = (session != null) ? (SignInManager) session.getAttribute("signInManager") : null;
        User user = null;
        try{
        user = signInManager.getUser();
        request.getRequestDispatcher("/manager/managerMainPage").forward(request, response);
        }catch(NullPointerException n){
            chain.doFilter(request, response);
        }
        if(user != null)
            request.getRequestDispatcher("/managerMainPage").forward(request, response);
              
    }
    
    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FilterManager:Initializing filter");
            }
        }
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

    public void setSignInManager(SignInManager signInManager) {
        this.signInManager = signInManager;
    }
    
}
