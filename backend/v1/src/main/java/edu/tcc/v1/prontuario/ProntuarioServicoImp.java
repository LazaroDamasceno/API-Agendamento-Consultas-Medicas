package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.cliente.ClienteServicoImp;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProntuarioServicoImp implements ProntuarioServico {

    private ProntuarioRepositorio repositorio;
    private ClienteServicoImp clienteServico;

    @Override
    public void cadastrarProntuario(String cpf) {
        Prontuario prontuario = new Prontuario();
        prontuario.setDataCriacao(LocalDateTime.now());
        Cliente cliente = clienteServico.exibirClientePeloCPF(cpf);
        prontuario.setCliente(cliente);
        repositorio.save(prontuario);
    }

    @Override
    public Prontuario exibirProntuarioPeloCliente(String cpf) {
        Prontuario prontuario = null;
        Cliente cliente = clienteServico.exibirClientePeloCPF(cpf);
        Optional<Prontuario> prontuarioOptional = repositorio.findByCliente(cliente);
        if (prontuarioOptional.isPresent()) prontuario = prontuarioOptional.get();
        return prontuario;
    }

    @Override
    public List<Prontuario> exibirProntuariosEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.exibirProntuariosEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Prontuario> exibirTodosOsProntuarios() {
        return repositorio.findAll();
    }

    @Override
    public void adicionarConsulta(String cpf, Consulta consulta) {
        Prontuario prontuario = exibirProntuarioPeloCliente(cpf);
        prontuario.getConsultas().add(consulta);
        repositorio.saveAndFlush(prontuario);
    }

    @Override
    public void associarMedico(String cpf, Medico medico) {
        Prontuario prontuario = exibirProntuarioPeloCliente(cpf);
        prontuario.setMedico(medico);
        repositorio.saveAndFlush(prontuario);
    }

}