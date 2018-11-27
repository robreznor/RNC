package cl.minsal.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.minsal.api.object.BarPlot;
import cl.minsal.api.service.PlotService;

@RestController
public class PlotController {
	
	@RequestMapping(value="/stadistic", method=RequestMethod.GET)
	private BarPlot search(){
		BarPlot barPlot = PlotService.cancerByType();
		return barPlot;
	}
}
