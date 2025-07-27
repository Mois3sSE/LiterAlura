package com.moises.literAlura.Servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class conversorDatos implements IconversorDatos {

    private ObjectMapper mapeo = new ObjectMapper(); 

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapeo.readValue(json, clase);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;  
    }

}
