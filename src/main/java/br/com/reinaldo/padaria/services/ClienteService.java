package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.ClienteDTO;
import br.com.reinaldo.padaria.entities.Cliente;
import br.com.reinaldo.padaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

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

    public void editarForm(int id, Model model) {
        clienteRepository.findById(id).ifPresent(cliente -> model.addAttribute("cliente", cliente));
    }

    public void editarSalvar(ClienteDTO cliente) {
        Cliente clienteEditado = new Cliente();
        clienteEditado.setId(cliente.id());
        clienteEditado.setNome(cliente.nome());
        clienteEditado.setTelefone(cliente.telefone());
        clienteEditado.setEndereco(cliente.endereco());
        clienteRepository.save(clienteEditado);
    }

    public void deletar(int id) {
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> buscarClientePorId(int id) {
        return clienteRepository.findById(id);
    }

    public Iterable<Cliente> buscarPorTelefone(String telefone) {
        return clienteRepository.findByTelefone(telefone);
    }
}
