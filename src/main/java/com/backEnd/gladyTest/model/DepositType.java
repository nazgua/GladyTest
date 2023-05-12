package com.backEnd.gladyTest.model;

public enum DepositType {
	GIFT("Gift"),
	MEAL("Meal"),
	INVALID_TYPE("Invalid Type");
	
    private final String textValue;

    DepositType(final String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }
}
