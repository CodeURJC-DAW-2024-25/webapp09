package es.grupo9.practica1;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CSRFHandlerConfiguration {
    
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CSRFHandlerInterceptor());
    }
}

@Component
class CSRFHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler,
        final ModelAndView modelAndView) throws Exception {
        
            if (modelAndView != null) {
                CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
                if (token != null) {
                    modelAndView.addObject("token", token.getToken());
                }
            }
    }
}
