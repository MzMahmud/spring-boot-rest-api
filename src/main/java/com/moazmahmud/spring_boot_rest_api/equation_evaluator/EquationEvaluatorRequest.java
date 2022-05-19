package com.moazmahmud.spring_boot_rest_api.equation_evaluator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquationEvaluatorRequest {
    private String equation;
    private Map<String, Object> variableValueMap;
}
