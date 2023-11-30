package org.example.Db;

import org.example.dto.AddressDtor;
import org.example.entity.Address;

import java.util.List;
import java.util.Objects;

public interface AddresDao extends GenericDao<Address>{
    List<Address> findAllByStreetNumber (String number);

    List<String> findALlStreetNameByNumber(String number);

    //Hay 3 formas de hacerlo
    List<Object[]> findAllStreetAndIdByNumber(String number);
    List<AddressDtor[]> findAllStreetAndIdByNumberAddresDtor(String number);


}
