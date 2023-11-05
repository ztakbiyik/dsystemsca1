package com.example.dsystems;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/customers")
public class CustomerResource {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnit");

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        // Return the created customer and a 201 Created response.
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("customerId") long customerId) {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, customerId);
        em.close();

        if (customer != null) {
            return Response.ok().entity(customer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("customerId") long customerId, Customer updatedCustomer) {
        EntityManager em = emf.createEntityManager();
        Customer existingCustomer;
        try {
            em.getTransaction().begin();
            existingCustomer = em.find(Customer.class, customerId);
            if (existingCustomer != null) {
                // Update the customer's information.
                existingCustomer.setName(updatedCustomer.getName());
                existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                existingCustomer.setAddress(updatedCustomer.getAddress());
                existingCustomer.setAnnualSalary(updatedCustomer.getAnnualSalary());
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        if (existingCustomer != null) {
            return Response.ok().entity(existingCustomer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Other methods, such as DELETE, can be added as needed.
}
