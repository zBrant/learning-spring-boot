package io.github.zbrant.restwithspring.controller;

import io.github.zbrant.restwithspring.exceptions.UnsupportedMathOperationException;
import io.github.zbrant.restwithspring.service.MathOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private final MathOperations mathOperations;

    public MathController(MathOperations mathOperations) {
        this.mathOperations = mathOperations;
    }

    @GetMapping("/sum/{n1}/{n2}")
    public Double sum(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        return mathOperations.sum(n1,n2);
    }

    @GetMapping("/sub/{n1}/{n2}")
    public Double sub(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        return mathOperations.sub(n1, n2);
    }

    @GetMapping("/mult/{n1}/{n2}")
    public Double mult(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        return mathOperations.mult(n1, n2);
    }

    @GetMapping("/div/{n1}/{n2}")
    public Double div(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        return mathOperations.div(n1, n2);
    }

    @GetMapping("/mean/{n1}/{n2}")
    public Double mean(@PathVariable(name = "n1") String n1, @PathVariable(name = "n2") String n2) throws UnsupportedMathOperationException{
        return mathOperations.mean(n1, n2);
    }

    @GetMapping("/sqrt/{n1}")
    public Double sqrt(@PathVariable(name = "n1") String n1) throws UnsupportedMathOperationException{
        return mathOperations.sqrt(n1);
    }
}
