package edu.tcc.v1.medico;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicoServicoImp implements MedicoServico {

    private MedicoRepositorio repositorio;

    @Override
    public Medico exibirMedicoPeloCRM(String crm) {
        Medico medico = null;
        Optional<Medico> medicoOptional = repositorio.findByCrm(crm);
        if (medicoOptional.isPresent()) medico = medicoOptional.get();
        return medico;
    }

    @Override
    public List<Medico> exibirTodosOsMedicos() {
        return repositorio.findAll();
    }

    @Override
    public void cadastrarMedico(CadastrarMedicoDTO dto) {
        Medico medico = new Medico(dto);
        repositorio.save(medico);
    }

    @Override
    public void demitirMedico(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        medico.setDataDemissao(LocalDateTime.now());
        repositorio.saveAndFlush(medico);
    }

}
