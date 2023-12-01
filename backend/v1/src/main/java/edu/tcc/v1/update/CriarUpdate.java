package edu.tcc.v1.update;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

public interface CriarUpdate {

    Update criarUpdate(LocalDateTime dataAgendamento);

}
