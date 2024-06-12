package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.ClienteDTO;
import br.com.reinaldo.padaria.entities.Cliente;
import br.com.reinaldo.padaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public void salvarCliente(ClienteDTO cliente) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(cliente.nome());
        novoCliente.setTelefone(cliente.telefone());
        novoCliente.setEndereco(cliente.endereco());
        clienteRepository.save(novoCliente);
    }

    public Iterable<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }
}
