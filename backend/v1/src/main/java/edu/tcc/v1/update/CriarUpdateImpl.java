package edu.tcc.v1.update;

import edu.tcc.v1.mensagemagendamento.MensagemAgendamentoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CriarUpdateImpl implements CriarUpdate {

    @Autowired
    private MensagemAgendamentoImpl ma;

    @Override
    public Update criarUpdate(LocalDateTime dataAgendamento) {
        String mensagem = ma.personalizarMensagemDeAgendamento(dataAgendamento);
        Update update = new Update();
        update.getMessage().setText(mensagem);
        return update;
    }

}
