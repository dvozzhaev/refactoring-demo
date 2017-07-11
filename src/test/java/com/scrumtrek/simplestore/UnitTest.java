package com.scrumtrek.simplestore;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitTest {
    @Test
    public void shouldAddFrequentPointsWhenNewMovieLongRented()
    {
        String customerName = "Customer1";

        Movie movieMock = mock(Movie.class);
        when(movieMock.getTitle())
                .thenReturn("New Release Movie 1");
        when(movieMock.getPriceCode())
                .thenReturn(PriceCodes.NewRelease);

        Rental rentalMock = mock(Rental.class);
        when(rentalMock.getDaysRented())
                .thenReturn(3);
        when(rentalMock.getMovie())
                .thenReturn(movieMock);

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
}
