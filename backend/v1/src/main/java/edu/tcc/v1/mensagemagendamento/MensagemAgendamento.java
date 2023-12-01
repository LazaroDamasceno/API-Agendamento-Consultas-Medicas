package edu.tcc.v1.mensagemagendamento;

import java.time.LocalDateTime;

public interface MensagemAgendamento {

    String personalizarMensagemDeAgendamento(LocalDateTime dataAgendamento);

}
