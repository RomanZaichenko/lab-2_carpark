package kpi.lab2.controller;

import org.apache.log4j.Logger;
import kpi.lab2.controller.command.Command;
import kpi.lab2.controller.validator.RegExp;
import kpi.lab2.exception.ApplicationException;
import kpi.lab2.utils.AttributesHolder;
import kpi.lab2.utils.ErrorsMessages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    public static final String REDIRECT = "redirect";

    private static final Logger logger = Logger.getLogger(FrontController.class);

    CommandHolder commandHolder;

    @Override
    public void init(){
        commandHolder = new CommandHolder();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandKey = getMethod(request) + CommandHolder.DELIMITER + getPath(request);
        logger.debug(commandKey);
        Command command = commandHolder.getCommand(commandKey);
        logger.debug(command);
        checkIfErrorIsPresent(request);
        executeCommand(request, response, command);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response, Command command) throws IOException {
        String error;
        try{
            String path = command.execute(request, response);
            logger.info(path);
            if(!isRedirected(path)) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
            }
            return;
        } catch (ApplicationException e) {
            error = e.getMessageKey();
            request.getSession().setAttribute(AttributesHolder.ERROR_MESSAGE, e.getMessageKey());
            logger.error(AttributesHolder.ERROR_MESSAGE, e);
        } catch (Exception e) {
            error = ErrorsMessages.NOT_EXCEPTED_ERROR;
            logger.error("Error", e);
            request.getSession().setAttribute(AttributesHolder.ERROR_MESSAGE,
                    ErrorsMessages.NOT_EXCEPTED_ERROR);
        }
        String regex = "/" + RegExp.NUMBER;
        String uri = request.getRequestURI();
        response.sendRedirect(request.getRequestURI()
                .replaceAll(regex, "")
                .replaceAll("/delete", "") + "?"+ AttributesHolder.ERROR_MESSAGE + "=" + error);
        logger.error(AttributesHolder.ERROR_MESSAGE);
    }

    private void checkIfErrorIsPresent(HttpServletRequest request) {
        request.setAttribute(AttributesHolder.ERROR_MESSAGE,
                request.getParameter(AttributesHolder.ERROR_MESSAGE));
    }

    private boolean isRedirected(String path) {
        return REDIRECT.equals(path);
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        logger.debug("URI " + path);
        return path.replaceAll(RegExp.NUMBER, "");
    }

    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }
}
