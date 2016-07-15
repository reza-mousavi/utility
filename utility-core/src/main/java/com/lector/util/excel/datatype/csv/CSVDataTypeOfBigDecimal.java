package com.lector.util.excel.datatype.csv;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 7/12/2016
 */
public class CSVDataTypeOfBigDecimal implements CSVDataType<BigDecimal> {

    @Override
    public String fromJava(BigDecimal value) {
        return value != null ? value.toString() : null;
    }

    @Override
    public Optional<BigDecimal> toJava(String value) {
        return Optional.ofNullable(value).map(BigDecimal::new);
    }

    @Override
    public Class<BigDecimal> getClazz() {
        return BigDecimal.class;
    }

    @Override
    public Supplier<? extends CSVDataType<BigDecimal>> getSupplier() {
        return CSVDataTypeOfBigDecimal::new;
    }
}