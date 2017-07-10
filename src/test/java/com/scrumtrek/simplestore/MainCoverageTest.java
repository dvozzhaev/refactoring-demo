package com.scrumtrek.simplestore;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * Target:
 *      - Branches 50%
 *      - Mutations: 80%
 */
public class MainCoverageTest {
    @Test
    public void shouldPrintStatement()
    {
        //Arrange
        Customer sut = new Customer("Customer1");
        Movie childrenMovie = new Movie("Children Movie 1", PriceCodes.Childrens);
        Movie newMovie = new Movie("New Release Movie 1", PriceCodes.NewRelease);
        Movie regularMovie = new Movie("Regular Movie 1", PriceCodes.Regular);
        Rental childrenRental = new Rental(childrenMovie, 1);
        Rental newRental = new Rental(newMovie, 1);
        Rental regularRental = new Rental(regularMovie, 1);

        //Act
        sut.addRental(childrenRental);
        sut.addRental(newRental);
        sut.addRental(regularRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tChildren Movie 1\t1.5\n" +
                "\tNew Release Movie 1\t3.0\n" +
                "\tRegular Movie 1\t2.0\n" +
                "Amount owed is 6.5\n" +
                "You earned 3 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldReturnName()
    {
        final String name = "Customer1";
        //Arrange
        Customer sut = new Customer(name);

        //Act

        //Assert
        assertEquals(name, sut.getName());
    }

    @Test
    public void shouldChargeExtraWhenRegularMovieLongRented()
    {
        //Arrange
        Customer sut = new Customer("Customer1");
        Movie regularMovie = new Movie("Regular Movie 1", PriceCodes.Regular);
        Rental regularRental = new Rental(regularMovie, 5);

        //Act
        sut.addRental(regularRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tRegular Movie 1\t6.5\n" +
                "Amount owed is 6.5\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldChargeExtraWhenChildrenMovieLongRented()
    {
        //Arrange
        Customer sut = new Customer("Customer1");
        Movie childrenMovie = new Movie("Children Movie 1", PriceCodes.Childrens);
        Rental childrenRental = new Rental(childrenMovie, 4);

        //Act
        sut.addRental(childrenRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tChildren Movie 1\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }
}
