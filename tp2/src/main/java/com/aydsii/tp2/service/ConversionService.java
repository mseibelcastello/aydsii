package com.aydsii.tp2.service;

import java.math.BigDecimal;
import java.util.List;

import com.aydsii.tp2.model.ConversionDTO;
import com.aydsii.tp2.model.ConversionResponse;
import com.aydsii.tp2.model.HistorialItemDTO;
import com.aydsii.tp2.model.tablas.HistorialConversion;
import com.aydsii.tp2.repository.HistorialConversionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConversionService {

    private final RestClient restCliente;
    private final HistorialConversionRepository historialRepository;

    public ConversionService(RestClient restCliente, HistorialConversionRepository historialRepository) {
        this.restCliente = restCliente;
        this.historialRepository = historialRepository;
    }

    public ConversionResponse consultarYGuardar(Double monto, String origen, String destino) {
        ConversionResponse conversion = convertir(monto, origen, destino);

        HistorialConversion registro = new HistorialConversion();
        registro.setMonedaOrigen(conversion.getMonedaOrigen());
        registro.setMonedaDestino(conversion.getMonedaDestino());
        registro.setMonto(BigDecimal.valueOf(conversion.getMontoOriginal()));
        registro.setMontoConvertido(BigDecimal.valueOf(conversion.getMontoConvertido()));
        registro.setTasa(BigDecimal.valueOf(conversion.getTasaCambio()));
        historialRepository.save(registro);

        return conversion;
    }

    public List<HistorialItemDTO> historial(String origen, String destino) {
        return historialRepository
                .findByMonedaOrigenAndMonedaDestinoOrderByFechaConsultaDesc(
                        origen.toUpperCase(), destino.toUpperCase())
                .stream()
                .map(h -> new HistorialItemDTO(h.getFechaConsulta(), h.getTasa()))
                .toList();
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
                        "No se pudo obtener la información del servicio externo");
            }

            // Con amount=monto, Frankfurter ya devuelve el monto convertido (no la tasa unitaria)
            Double montoConvertido = respuesta.getRates().get(monedaDestino);
            if (montoConvertido == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Moneda destino no válida: " + monedaDestino);
            }

            double tasa = montoConvertido / monto;

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
                        "Moneda no válida: " + monedaOrigen + "/" + monedaDestino);
            }
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "El servicio externo devolvió un error");
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "No se pudo conectar con el servicio externo (timeout o sin respuesta)");
        }
    }

}