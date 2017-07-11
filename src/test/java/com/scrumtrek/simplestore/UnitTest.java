package com.scrumtrek.simplestore;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitTest {

    private final String customerName = "Customer1";

    private Rental createRental(String movieName, PriceCodes priceCode, int daysRented) {
        Movie movieMock = mock(Movie.class);
        when(movieMock.getTitle())
                .thenReturn(movieName);
        when(movieMock.getPriceCode())
                .thenReturn(priceCode);

        Rental rentalMock = mock(Rental.class);
        when(rentalMock.getDaysRented())
                .thenReturn(daysRented);
        when(rentalMock.getMovie())
                .thenReturn(movieMock);
        return rentalMock;
    }

    @Test
    public void shouldAddFrequentPointsWhenNewMovieLongRented()
    {
        Rental rentalMock = createRental("New Release Movie 1", PriceCodes.NewRelease, 3);

        //Arrange
        Customer sut = new Customer(customerName);

        //Act
        sut.addRental(rentalMock);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tNew Release Movie 1\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points.", sut.Statement());
    }


    @Test
    public void shouldChargeExtraWhenRegularMovieLongRented()
    {
        Rental rentalMock = createRental("Regular Movie 1", PriceCodes.Regular, 5);

        //Arrange
        Customer sut = new Customer(customerName);

        //Act
        sut.addRental(rentalMock);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tRegular Movie 1\t6.5\n" +
                "Amount owed is 6.5\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldChargeExtraWhenChildrenMovieLongRented()
    {
        Rental rentalMock = createRental("Children Movie 1", PriceCodes.Childrens, 5);

        //Arrange
        Customer sut = new Customer(customerName);

        //Act
        sut.addRental(rentalMock);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tChildren Movie 1\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldNotChargeExtraWhenRegularMovieShortRented()
    {
        Rental rentalMock = createRental("Regular Movie 1", PriceCodes.Regular, 1);

        //Arrange
        Customer sut = new Customer(customerName);

        //Act
        sut.addRental(rentalMock);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tRegular Movie 1\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }

    @Test
    public void shouldNotChargeExtraWhenChildrenMovieShortRented()
    {
        Rental rentalMock = createRental("Children Movie 1", PriceCodes.Childrens, 1);

        //Arrange
        Customer sut = new Customer(customerName);

        //Act
        sut.addRental(rentalMock);

        //Assert
        assertEquals("Rental record for Customer1\n" +
                "\tChildren Movie 1\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points.", sut.Statement());
    }
}
