package io.github.zbrant.restwithspring;

import io.github.zbrant.restwithspring.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/sum/{n1}/{n2}")
    public Double sum(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) + converToDouble(n2);
    }

    @GetMapping("/sub/{n1}/{n2}")
    public Double sub(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) - converToDouble(n2);
    }
    @GetMapping("/mult/{n1}/{n2}")
    public Double mult(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) * converToDouble(n2);
    }
    @GetMapping("/div/{n1}/{n2}")
    public Double div(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return converToDouble(n1) / converToDouble(n2);
    }
    @GetMapping("/mean/{n1}/{n2}")
    public Double mean(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        if(!isNumeric(n1) || !isNumeric(n2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return (converToDouble(n1) + converToDouble(n2))/2;
    }
    @GetMapping("/sqrt/{n1}")
    public Double sqrt(@PathVariable(name = "n1") String n1) throws UnsupportedMathOperationException{
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
