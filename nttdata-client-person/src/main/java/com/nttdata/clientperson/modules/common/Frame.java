package com.nttdata.clientperson.modules.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase Frame representa una estructura generica que contiene un codigo, un mensaje y una respuesta.
 * Se utiliza para encapsular respuestas en la aplicacion.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Frame {

    private Integer code;
    private String message;
    private Object response;

}
