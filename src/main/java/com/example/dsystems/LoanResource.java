package com.example.dsystems;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/loans")
public class LoanResource {

    // Inject a service or data access layer to interact with the database
    private LoanService loanService;

    public LoanResource() {
        // Initialize or inject the loanService
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Loan> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return loans;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Loan createLoan(Loan loan) {
        Loan createdLoan = loanService.createLoan(loan);
        return createdLoan;
    }

    @GET
    @Path("/{loanId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Loan getLoan(@PathParam("loanId") long loanId) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            return loan;
        } else {
            throw new NotFoundException("Loan with ID " + loanId + " not found");
        }
    }

    @POST
    @Path("/{loanId}/payments")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Payment makeLoanPayment(@PathParam("loanId") long loanId, Payment payment) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan == null) {
            throw new NotFoundException("Loan with ID " + loanId + " not found");
        }

        Payment recordedPayment = loanService.makeLoanPayment(loan, payment);
        return recordedPayment;
    }
}
