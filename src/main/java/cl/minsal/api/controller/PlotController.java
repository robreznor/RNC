package cl.minsal.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.minsal.api.object.BarPlot;
import cl.minsal.api.service.PlotService;

@RestController
public class PlotController {
	
	@RequestMapping(value="/stadistic/cancer_by_type", method=RequestMethod.GET)
	private BarPlot cancerByType(){
		BarPlot barPlot = PlotService.cancerByType();
		return barPlot;
	}

	@RequestMapping(value="/stadistic/cancer_by_age", method=RequestMethod.GET)
	private BarPlot cancerByAge(){
		BarPlot barPlot = PlotService.cancerByAge();
		return barPlot;
	}
}
