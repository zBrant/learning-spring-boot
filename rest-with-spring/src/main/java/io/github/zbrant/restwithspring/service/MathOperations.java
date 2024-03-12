package io.github.zbrant.restwithspring.service;

import io.github.zbrant.restwithspring.exceptions.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathOperations {
    public Double sum(String n1, String n2){
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) + converToDouble(n2);
    }

    public Double sub(String n1, String n2){
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) - converToDouble(n2);
    }

    public Double mult(String n1, String n2){
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) * converToDouble(n2);
    }

    public Double div(String n1, String n2){
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) / converToDouble(n2);
    }
    public Double mean(String n1, String n2){
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return (converToDouble(n1) + converToDouble(n2))/2;
    }
    public Double sqrt(String n1){
        if(!isNumeric(n1)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return Math.sqrt(converToDouble(n1));
    }

    private Double converToDouble(String n) {
        if (n == null) return 0D;

        String number = n.replaceAll(",", ".");
        if (isNumeric(n)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String n) {
        if (n == null) return false;
        String number = n.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
