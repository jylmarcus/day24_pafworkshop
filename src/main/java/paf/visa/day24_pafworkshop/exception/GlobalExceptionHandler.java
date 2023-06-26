package paf.visa.day24_pafworkshop.exception;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
   
    public ModelAndView handleUnableToCreateOrderException(UnableToCreateOrderException ex, HttpServletRequest request) {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(400);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage("Order was unable to be created");
        errMsg.setDescription(request.getRequestURI());

        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);
        return mav;
    }
}
