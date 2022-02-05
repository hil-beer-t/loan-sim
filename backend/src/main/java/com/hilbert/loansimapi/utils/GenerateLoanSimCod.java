package com.hilbert.loansimapi.utils;

import java.util.Calendar;

/*
*   Generate "YEAR + MONTH + DAY + SECONDS + clientId" unique cod for one ongoing loan simulation
*
* */
public class GenerateLoanSimCod {
    public Long clientId;

    public GenerateLoanSimCod() {
    }

    public GenerateLoanSimCod(Long clientId) {
        this.clientId = clientId;
    }

    public String getCod() {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get((Calendar.DAY_OF_MONTH));
        int seconds = c.get((Calendar.SECOND));

        StringBuilder loanCod = new StringBuilder();
        loanCod.append(year).append("0").append(month+1).append(day).append(seconds).append(clientId);

        return loanCod.toString();
    }
}
