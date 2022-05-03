package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {

    @Test
    public void random() {
        Random randomGenerator = new Random();
        int upperBoundExcluded = 10;
        int actual = randomGenerator.nextInt(upperBoundExcluded);
        assertThat(actual).isLessThan(10);
    }

    @Test
    public void rounding() {
        BigDecimal bigDecimal = new BigDecimal("1.23456789");
        BigDecimal actual = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        assertThat(actual).isLessThan(new BigDecimal("1.235"));
    }

}