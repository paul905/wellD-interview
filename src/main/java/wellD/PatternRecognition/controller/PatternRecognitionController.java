package wellD.PatternRecognition.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wellD.PatternRecognition.model.Point;
import wellD.PatternRecognition.service.PatternRecognitionService;

/**
 * 
 * @author Paolo Abatemarco
 *
 */
@RestController()
public class PatternRecognitionController {

	private PatternRecognitionService service;

	@Autowired
	public PatternRecognitionController(PatternRecognitionService service) {
		this.service = service;
	}

	@GetMapping("/space")
	public Set<Point> getSpace() {
		Set<Point> space = service.getAllPoints();
		return space;

	}

	@GetMapping("/lines/{n}")
	public List<Set<Point>> getLines(@PathVariable int n) {
		List<Set<Point>> lines = service.getLineSegments(n);
		return lines;
	}

	@PostMapping("/point")
	public void addPoint(@RequestBody Point p) {
		service.addPoint(p);

	}

	@DeleteMapping("/space")
	public void deleteSpace() {
		service.removeAllPoints();
	}

}
