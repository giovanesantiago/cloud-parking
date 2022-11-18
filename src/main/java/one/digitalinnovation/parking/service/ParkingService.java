package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    // Incializando com dados mocados para teste antes de criação do banco de dados
    static {
        var id  = getUUID();
        Parking parking = new Parking(id, "OMS-1111", "sc", "CELTA", "PRETO");
        parkingMap.put(id, parking);
    }

    // Retorna todos os veículos
    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }



    // Metodo para gerar id
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");

    }

}
