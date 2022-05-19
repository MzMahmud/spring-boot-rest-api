package com.moazmahmud.spring_boot_rest_api.equation_evaluator;

import org.springframework.stereotype.Service;

@Service
public interface EquationEvaluatorService {
    EquationEvaluatorResponse evaluate(EquationEvaluatorRequest equationEvaluatorRequest);
}
