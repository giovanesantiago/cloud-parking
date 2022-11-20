package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckOut {

    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    public static final double ONE_HOUR_VALUE = 5.00;
    public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 20.00;

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        // Armazenando a diferença de tempo ENTRADA/SAIDA em minutos
        Long tempoEstacionamentoMinutos = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = 0.0;

        if (tempoEstacionamentoMinutos <= ONE_HOUR) return ONE_HOUR_VALUE;

        if (tempoEstacionamentoMinutos <= TWENTY_FOUR_HOUR){
            bill = ONE_HOUR_VALUE;
            int tempoEstacionamentohoras = (int) (tempoEstacionamentoMinutos / ONE_HOUR);

            for (int i = 0; i < tempoEstacionamentohoras; i++) {
                bill += ADDITIONAL_PER_HOUR_VALUE;
            }
            return bill;
        }

        int tempoEstacionamentoDias = (int) (tempoEstacionamentoMinutos / TWENTY_FOUR_HOUR);
        for (int i = 0; i < tempoEstacionamentoDias; i++) {
            bill += DAY_VALUE;
        }
        return bill;

    }


    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }


}
