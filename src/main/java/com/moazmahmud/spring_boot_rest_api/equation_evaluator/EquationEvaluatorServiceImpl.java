package com.moazmahmud.spring_boot_rest_api.equation_evaluator;

import org.springframework.stereotype.Service;

@Service
public class EquationEvaluatorServiceImpl implements EquationEvaluatorService{
    @Override
    public EquationEvaluatorResponse evaluate(EquationEvaluatorRequest equationEvaluatorRequest) {
        return EquationEvaluatorResponse
                .builder()
                .equation(equationEvaluatorRequest.getEquation())
                .variableValueMap(equationEvaluatorRequest.getVariableValueMap())
                .value(0d)
                .build();
    }
}
