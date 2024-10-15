package com.nttdata.accountmovements.modules.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Clase ApiResponse que maneja las respuestas de la API.
 */
public class ApiResponse {

    private static Integer code;
    private static String message;

    /**
     * Metodo que retorna una respuesta HTTP con estado OK (200) sin contenido adicional.
     *
     * @return ResponseEntity con un objeto Frame y estado HTTP OK.
     */
    public static ResponseEntity<Frame> ok() {
        code = HttpStatus.OK.value();
        return sendResponse(HttpStatus.OK, MessageResponse.SUCCESS.getMessage(), null);
    }

    /**
     * Metodo que retorna una respuesta HTTP con estado OK (200) y contenido adicional.
     *
     * @param response Objeto adicional que se incluye en la respuesta.
     * @return ResponseEntity con un objeto Frame, contenido adicional y estado HTTP OK.
     */
    public static ResponseEntity<Frame> ok(Object response) {
        code = HttpStatus.OK.value();
        return sendResponse(HttpStatus.OK, MessageResponse.SUCCESS.getMessage(), response);
    }

    /**
     * Metodo que retorna una respuesta HTTP con estado BAD REQUEST (400) en caso de excepcion.
     *
     * @param exception Excepcion que se incluye en la respuesta.
     * @return ResponseEntity con un objeto Frame, mensaje de error y estado HTTP BAD REQUEST.
     */
    public static ResponseEntity<Frame> error(Exception exception) {
        code = HttpStatus.BAD_REQUEST.value();
        return sendResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getStackTrace());
    }
    
    /**
     * Metodo privado que construye y envia la respuesta HTTP.
     *
     * @param httpStatus Estado HTTP de la respuesta.
     * @param message    Mensaje de la respuesta.
     * @param response   Contenido adicional de la respuesta.
     * @return ResponseEntity con un objeto Frame y el estado HTTP especificado.
     */
    private static ResponseEntity<Frame> sendResponse(HttpStatus httpStatus, String message, Object response) {
        Frame frame = new Frame();
        frame.setCode(code);
        frame.setMessage(message);
        frame.setResponse(response);
        return new ResponseEntity<>(frame, httpStatus);
    }

}
