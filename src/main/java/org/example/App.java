package org.example;

import org.example.dao.ClienteDAO;
import org.example.entity.Cliente;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }

    public static void main( String[] args ) {

        ClienteDAO clienteDAO = new ClienteDAO();

        // Añadimos los clientes
        Cliente cliente1 = new Cliente(1L,"Jorge",12L,"activo");
        clienteDAO.insertarCliente(cliente1);
        Cliente cliente2 = new Cliente(2L,"Paco",0L,"inactivo");
        clienteDAO.insertarCliente(cliente2);
        Cliente cliente3 = new Cliente(3L,"Raul",3L,"inactivo");
        clienteDAO.insertarCliente(cliente3);
        Cliente cliente4 = new Cliente(4L,"Rafa",8L,"activo");
        clienteDAO.insertarCliente(cliente4);
        Cliente cliente5 = new Cliente(5L,"Pablo",17L,"activo");
        clienteDAO.insertarCliente(cliente5);

        // Obtener un cliente por su id
        Long idCliente = 4L;
        System.out.println( "Get cliente con id " + idCliente + ": ");
        clienteDAO.getCliente(idCliente);

        // Listar clientes activos con total de ventas mayor a cantidad concreta
        Long cantidadVentas = 10L;
        System.out.println( "Lista de los mejores clientes:" );
        clienteDAO.listarMejoresClientes(cantidadVentas);

        // Mostrar estadisticas (total ventas, promedio ventas clientes activos y cantidad clientes
        // inactivos con total ventas mayor a 0)
        System.out.println( "Estadisticas de los clientes:" );
        clienteDAO.estadisticas();
    }
}
