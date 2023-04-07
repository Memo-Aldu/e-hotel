package org.com.ehotel.service.user;

import org.com.ehotel.dto.user.CustomerDTO;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/5/2023, Wednesday
 **/
public interface CustomerService {
    CustomerDTO findCustomerByNas(String nas);
    CustomerDTO findCustomerByEmail(String email);
    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO, String email);
    void delete(String nas);
    void deleteByEmail(String email);
}
