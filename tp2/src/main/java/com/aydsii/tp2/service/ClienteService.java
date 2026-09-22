package com.aydsii.tp2.service;

import com.aydsii.tp2.repository.ClienteRepository;
import com.aydsii.tp2.model.Cliente;
import com.aydsii.tp2.model.ClienteDTO;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrar(ClienteDTO cli) {
        Cliente cliente = new Cliente();

        cliente.setNombre(cli.getNombre());
        cliente.setApellido(cli.getApellido());
        cliente.setEmail(cli.getEmail());
        cliente.setTelefono(cli.getTelefono());

        return clienteRepository.save(cliente);
        // .save envia losdatos del cliente cargados en eldto y los manda al jpa, el
        // cual completa la informacion del id y la fecha
    }

    public Cliente registrarValidado(ClienteDTO dto) {

        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya est registrado");
        }

        return registrar(dto);
    }

}
