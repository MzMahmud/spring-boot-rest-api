package com.moazmahmud.spring_boot_rest_api.equation_evaluator;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Builder
@Getter
public class EquationEvaluatorResponse {
    private String equation;
    private Map<String, Object> variableValueMap;
    private Double value;
}
