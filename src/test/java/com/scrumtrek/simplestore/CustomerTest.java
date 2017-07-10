package com.scrumtrek.simplestore;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * Target:
 *      - Branches 50%
 *      - Mutations: 80%
 */
public class CustomerTest {
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
    public void shouldAddFrequentPointsWhenNewMovieLongRented()
    {
        //Arrange
        Customer sut = new Customer("Customer1");
        Movie newMovie = new Movie("New Release Movie 1", PriceCodes.NewRelease);
        Rental newRental = new Rental(newMovie, 3);

        //Act
        sut.addRental(newRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tNew Release Movie 1\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points.", sut.Statement());
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
        Rental childrenRental = new Rental(childrenMovie, 5);

        //Act
        sut.addRental(childrenRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tChildren Movie 1\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldNotChargeExtraWhenRegularMovieShortRented()
    {
        //Arrange
        Customer sut = new Customer("Customer1");
        Movie regularMovie = new Movie("Regular Movie 1", PriceCodes.Regular);
        Rental regularRental = new Rental(regularMovie, 1);

        //Act
        sut.addRental(regularRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tRegular Movie 1\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldNotChargeExtraWhenChildrenMovieShortRented()
    {
        //Arrange
        Customer sut = new Customer("Customer1");
        Movie childrenMovie = new Movie("Children Movie 1", PriceCodes.Childrens);
        Rental childrenRental = new Rental(childrenMovie, 1);

        //Act
        sut.addRental(childrenRental);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tChildren Movie 1\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldShangeRentalCode()
    {
        //Arrange
        Movie sut = new Movie("Test Movie", PriceCodes.NewRelease);

        //Act
        sut.setPriceCode(PriceCodes.Regular);

        //Assert
        assertEquals(PriceCodes.Regular, sut.getPriceCode());
    }
}
