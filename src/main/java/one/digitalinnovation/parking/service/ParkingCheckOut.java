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
        Long tempoEstacionadoMinutos = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = 0.0;

        if (tempoEstacionadoMinutos <= ONE_HOUR) return ONE_HOUR_VALUE;

        if (tempoEstacionadoMinutos <= TWENTY_FOUR_HOUR){
            bill = ONE_HOUR_VALUE;
            int tempoEstacionadohoras = (int) (tempoEstacionadoMinutos / ONE_HOUR);

            for (int i = 0; i < tempoEstacionadohoras; i++) {
                bill += ADDITIONAL_PER_HOUR_VALUE;
            }
            return bill;
        }

        int tempoEstacionadoDias = (int) (tempoEstacionadoMinutos / TWENTY_FOUR_HOUR);
        for (int i = 0; i < tempoEstacionadoDias; i++) {
            bill += DAY_VALUE;
        }
        return bill;

    }


    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }


}
