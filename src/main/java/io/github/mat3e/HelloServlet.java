package io.github.mat3e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private final static String NAME_PARAM = "name";
    private final static String LANG_PARAM = "lang";

    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    /**
     * Servlet container needs it
     */
    public HelloServlet() {
        this(new HelloService());
    } //binding Servlet with Service

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("request got");
        resp.getWriter().write(service.prepareGreeting(req.getParameter(NAME_PARAM), req.getParameter(LANG_PARAM))); //getParameter returns parameter as string
    }
}
