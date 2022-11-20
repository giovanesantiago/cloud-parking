package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    // Incializando com dados mocados para teste antes de criação do banco de dados
   /* static {
        var id  = getUUID();
        Parking parking = new Parking(id, "OMS-1111", "BA", "BMW X1", "BRANCA");
        parkingMap.put(id, parking);
    }*/

    // Retorna todos os veículos
    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }


    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;

    }

    // Metodo para gerar id
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");

    }


    public Parking exit(String id) {
        // Recuperar o estacionado
        // atualizar data de saida
        // calcular o valor
        return null;
    }
}
