package com.moazmahmud.spring_boot_rest_api.equation_evaluator;

import com.moazmahmud.spring_boot_rest_api.common.BaseRestController;
import com.moazmahmud.spring_boot_rest_api.common.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/equation-evaluator")
@AllArgsConstructor
public class EquationEvaluatorController extends BaseRestController {

    private final EquationEvaluatorService equationEvaluatorService;

    @PostMapping
    public RestResponse getProducts(
            @RequestBody EquationEvaluatorRequest equationEvaluatorRequest
    ) {
        var equationEvaluatorResponse = equationEvaluatorService.evaluate(equationEvaluatorRequest);
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(equationEvaluatorResponse)
                .build();
    }
}
