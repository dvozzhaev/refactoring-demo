package com.scrumtrek.simplestore;

public class Rental {
    private Movie m_Movie;
    private int m_DaysRented;

    public Rental(Movie movie, int daysRented) {
        m_Movie = movie;
        m_DaysRented = daysRented;
    }

    public int getDaysRented() {
        return m_DaysRented;
    }

    public Movie getMovie() {
        return m_Movie;
    }

    public double price() {
        double thisAmount = 0;

        // Determine amounts for each line
        switch (getMovie().getPriceCode()) {
            case XXX:
            case Regular:
                thisAmount += 2;
                if (this.getDaysRented() > 2) {
                    thisAmount += (this.getDaysRented() - 2) * 1.5;
                }
                if (getMovie().getPriceCode() == PriceCodes.XXX)
                    thisAmount *= 0.85;
                break;

            case NewRelease:
                thisAmount += getDaysRented() * 3;
                break;

            case Childrens:
                thisAmount += 1.5;
                if (getDaysRented() > 3) {
                    thisAmount = (getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return thisAmount;
    }
}

