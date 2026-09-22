package com.aydsii.tp2.service;

import com.aydsii.tp2.model.ConversionDTO;
import com.aydsii.tp2.model.ConversionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConversionService {

    private final RestClient restCliente;

    public ConversionService(RestClient restCliente) {
        this.restCliente = restCliente;
    }

    public ConversionResponse convertir(Double monto, String origen, String destino) {
        String monedaOrigen = origen.toUpperCase();
        String monedaDestino = destino.toUpperCase();

        String url = "https://api.frankfurter.app/latest?amount="
                + monto
                + "&from=" + monedaOrigen
                + "&to=" + monedaDestino;

        try {
            ConversionDTO respuesta = restCliente.get().uri(url).retrieve().body(ConversionDTO.class);

            if (respuesta == null || respuesta.getRates() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                        "No se pudo obtener la informacion del servicio externo");
            }

            Double tasa = respuesta.getRates().get(monedaDestino);
            if (tasa == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Moneda destino no valida: " + monedaDestino);
            }

            double montoConvertido = monto * tasa;

            ConversionResponse conversion = new ConversionResponse();
            conversion.setMontoOriginal(monto);
            conversion.setMonedaOrigen(monedaOrigen);
            conversion.setMonedaDestino(monedaDestino);
            conversion.setTasaCambio(tasa);
            conversion.setMontoConvertido(montoConvertido);
            conversion.setFecha(respuesta.getDate());

            return conversion;
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Moneda no valida: " + monedaOrigen + "/" + monedaDestino);
            }
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "El servicio externo devolvio un error");
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "No se pudo conectar con el servicio externo (timeout o sin respuesta)");
        }
    }

}