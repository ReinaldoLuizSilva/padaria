package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.FuncionarioDTO;
import br.com.reinaldo.padaria.entities.Funcionario;
import br.com.reinaldo.padaria.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public Iterable<Funcionario> buscarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public void salvarFuncionario(FuncionarioDTO funcionario) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(funcionario.nome());
        novoFuncionario.setCpf(funcionario.cpf());
        novoFuncionario.setEndereco(funcionario.endereco());
        novoFuncionario.setCargo(funcionario.cargo());
        novoFuncionario.setSalario(funcionario.salario());
        funcionarioRepository.save(novoFuncionario);
    }

    public void editarForm(int id, Model model) {
        funcionarioRepository.findById(id).ifPresent(funcionario -> model.addAttribute("funcionario", funcionario));
    }

    public void editarSalvar(FuncionarioDTO funcionario) {
        Funcionario funcionarioEditado = new Funcionario();
        funcionarioEditado.setId(funcionario.id());
        funcionarioEditado.setNome(funcionario.nome());
        funcionarioEditado.setCpf(funcionario.cpf());
        funcionarioEditado.setEndereco(funcionario.endereco());
        funcionarioEditado.setCargo(funcionario.cargo());
        funcionarioEditado.setSalario(funcionario.salario());
        funcionarioRepository.save(funcionarioEditado);
    }

    public void deletar(int id) {
        funcionarioRepository.deleteById(id);
    }
}
