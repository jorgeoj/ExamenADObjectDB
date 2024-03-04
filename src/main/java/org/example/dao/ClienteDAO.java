package org.example.dao;

import org.example.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }

    public void insertarCliente(Cliente cliente) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        }
        finally {
            entityManager.close();
        }
    }

    public void getCliente(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            List<Cliente> listaClientes = query.getResultList();
            Cliente clienteFinal = new Cliente();
            // Recorremos la lista de clientes para guardar luego el que coincida con el id
            for(Cliente c: listaClientes){
                if(c.getId().equals( id )){
                    clienteFinal = c;
                }
            }
            System.out.println(clienteFinal);
        }
        finally {
            entityManager.close();
        }
    }

    public void listarMejoresClientes(Long cantidad) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            List<Cliente> listaClientes = query.getResultList(); // Lista con todos los clientes
            List<Cliente> listaMejores = new ArrayList<>(); // Lista vacia para añadir los clientes
            for(Cliente c: listaClientes){
                if(c.getTotalVentas() > cantidad && c.getEstado().equals("activo") ){
                    listaMejores.add(c);
                }
            }
            // Iteramos para  escribir toda la lista de clientes
            for(Cliente c: listaMejores){
                System.out.println(c);
            }
        }
        finally {
            entityManager.close();
        }
    }

    public void estadisticas() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Long totalVentas = 0L;
        Double promedioVentas = 0.0;
        Long clientesInactivos = 0L;
        Double numActivos = 0.0;

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            List<Cliente> listaClientes = query.getResultList();
            // Recorremos la lista de clientes
            for(Cliente c: listaClientes){
                totalVentas += c.getTotalVentas(); // Por cada cliente suma las ventas
                if(c.getEstado().equals("activo")){
                    numActivos++;
                    promedioVentas += c.getTotalVentas();
                }
                else{
                    // Si el cliente esta inactivo y las ventas son mayores a 0, lo añadimos
                    if(c.getTotalVentas()>0){
                        clientesInactivos++;
                    }
                }
                System.out.println(c);
            }
            promedioVentas = promedioVentas / numActivos; // Calculamos el promedio de ventas
            System.out.println("Total de ventas: " + totalVentas);
            System.out.println("Promedio de ventas de los clientes activos: " + promedioVentas);
            System.out.println("Cantidad de clientes inactivos con total de ventas mayor a 0: " + clientesInactivos);
        }
        finally {
            entityManager.close();
        }
    }
}
